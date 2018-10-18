import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import java.util.Random;

public class GraphsPlosca extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JLabel label;
	private Test test;
	private VsebinskaPlosca vsebinskaPlosca;
	
	
	private final int POLMER = 300;
	
	private boolean histogram = false;
	
	private Color[] colors;
	private double[] podatki;
	private ArrayList<String> kategorije;
	
	private JComboBox comboBox;
	
	public GraphsPlosca(int x, int y, int width, int height, Color color, Test test, VsebinskaPlosca vsebinskaPlosca) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.test = test;
		this.vsebinskaPlosca = vsebinskaPlosca;
		
		setBackground(color);				
		
		
		/*
		 *Podatki in barve se morajo �e tukaj inicilializirati, ker �e se �ele v paint()
		 *bo prepozno, ker paint() se spro�i ko se nalo�i GUI 
		 * */
		//this.podatki = new double[]{5, 12, 4, 16, 25};
		dobiPodatke();


		String[] data = {"Categories", "Income/Expense", "Expense per Month"};
		this.comboBox = new JComboBox(data);
		this.comboBox.addActionListener(this);
		this.add(this.comboBox);
	}
	
	public void dobiIncomeExpensePodatke() {
		ArrayList<Transakcija> expense = this.test.vrniTipTransakcij("Expense");
		ArrayList<Transakcija> income = this.test.vrniTipTransakcij("Income");
		
		double[] podatki = new double[2];
		
		for(Transakcija transakcija : expense) {
			podatki[0] += transakcija.getAmount();
		}
		for(Transakcija transakcija : income) {
			podatki[1] += transakcija.getAmount();
		}
		
		this.kategorije.clear();
		this.kategorije.add("Expense");
		this.kategorije.add("Income");
		this.colors = naborBarv(2);
		this.podatki = podatki;
	}
	
	public void dobiPodatke() {	
		
		ArrayList<String> kategorije = this.test.vrniKategorije();
		/*
		 * Zbri�emo za�asno kategorijo "Income" iz 
		 * obravnave, saj predstavlja Income.
		 * */
		for(int i = 0; i < kategorije.size(); i++) {
			if(kategorije.get(i).equals("Income")) {
				kategorije.remove(i);
			}
		}
		
		double[] podatki = new double[kategorije.size()];

		for(int i = 0; i < kategorije.size(); i++) {
			ArrayList<Transakcija> transakcijeIzKategorije = this.test.vrniTransakcijeIzKategorije(kategorije.get(i));
			for(Transakcija x : transakcijeIzKategorije) {
				podatki[i] += x.getAmount();
			}
		}
		//Urejanje kategorij po njihovi porabi
		for(int i = 0; i < kategorije.size() - 1; i++) {
			for(int j = i+1; j < kategorije.size(); j++) {
				if(podatki[i] < podatki[j]) {
					//Uredimo �tevilke
					double a = podatki[i];
					podatki[i] = podatki[j];
					podatki[j] = a;
					
					//Uredimo kategorije
					String kat = kategorije.get(i);
					kategorije.set(i, kategorije.get(j));
					kategorije.set(j, kat);

				}
			}
		}
		if(podatki.length > 6) {
			double[] noviPodatki = new double[7];
			for(int i = 0; i < 7; i++) {
				noviPodatki[i] = podatki[i];
			}
			double other = 0.0;

			for(int i = 6; i < podatki.length; i++) {
				other += podatki[i];
				kategorije.remove(6);
			}
			noviPodatki[6] = other;
			kategorije.add("Other");
			System.out.println(kategorije.size());
			podatki = noviPodatki;
		}
		
		this.kategorije = kategorije;
		this.podatki = podatki;
		this.colors = naborBarv(this.podatki.length);

	}
	
	public String[] urediMesece() {
		String[] meseci = {"Januar", "Februar", "March",
				"April", "May", "June", "July", "August",
				"September", "October", "November", "December"};
		LocalDate localDate = LocalDate.now();
		int trenuntiMesec = localDate.getMonthValue() - 1;
		
		String[] praviMeseci = new String[12];
		
		for(int i = 0; i < 12; i++) {
			
			praviMeseci[(12-(trenuntiMesec+1)+i) % 12] = meseci[i];
		}
		meseci = praviMeseci;
		return meseci;
	}
	
	public double[] porabaPoMesecih() {
		
		LocalDate localDate = LocalDate.now();
		String datum = localDate.toString();
		
		int trenutniMesec = Integer.parseInt(datum.substring(5, 7));
		int trenutnoLeto = Integer.parseInt(datum.substring(0, 4));
				
		ArrayList<Transakcija> transakcije= this.test.vrniTipTransakcij("Expense");
		double[] amountPerMonth = new double[12];
		/*
		 * Izbira tistih transakcij, ki so prave za obravnavo
		 * -> torej tiste ki so se zgodile v tem obdobju
		 * */
		for(int i = 0; i < transakcije.size(); i++) {
			int mesec = Integer.parseInt(transakcije.get(i).getDate().substring(3,5));
			int leto = Integer.parseInt(transakcije.get(i).getDate().substring(6,10));
			
			if(leto == trenutnoLeto || (leto == trenutnoLeto - 1 && mesec > trenutniMesec)) {
				//it is okay
				amountPerMonth[(12-(trenutniMesec+1)+mesec) % 12] += transakcije.get(i).getAmount();
			}else {
				transakcije.remove(i);
			}
		}
		
		return amountPerMonth;
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
		if(histogram == true) {
			String[] oznake = urediMesece();
			double[] vrednosti = porabaPoMesecih();
			
			int odmikOdRoba = 15;
			
			g.drawLine(odmikOdRoba, this.height - odmikOdRoba, this.width - odmikOdRoba, this.height - odmikOdRoba);
			g.drawLine(odmikOdRoba, this.height - odmikOdRoba, odmikOdRoba, 3*odmikOdRoba);
			
			int enotaDolzine = (this.width - odmikOdRoba) / 12;
			
			for(int i = 0; i < 12; i++) {
				g.drawLine(odmikOdRoba + i*enotaDolzine, this.height - odmikOdRoba, odmikOdRoba + i*enotaDolzine, this.height - 30);
				int widthOfText = g.getFontMetrics().stringWidth(oznake[i].substring(0, 3));
				g.drawString(oznake[i].substring(0, 3), ((odmikOdRoba + i*enotaDolzine) + enotaDolzine / 2) - widthOfText/2, this.height - odmikOdRoba/5);
			}
			
			int visinaGrafa = this.height - 4*odmikOdRoba;
			
			double max = 0;
			//Izlu��imo max za mero in 
			for(int i = 0; i < 12; i++) {
				if(vrednosti[i] > max) {
					max = vrednosti[i];
				}
			}
			
			double[] delezi = new double[12];
			//Izra�unamo dele�e za izris grafa in ga izri�emo
			
			for(int i = 0; i < 12; i++) {
				delezi[i] = vrednosti[i]/max;
				g.setColor(Color.BLACK);
				g.drawRect(odmikOdRoba + i*enotaDolzine, this.height - odmikOdRoba - (int)(delezi[i]*visinaGrafa), enotaDolzine, (int)(delezi[i]*visinaGrafa));
				g.setColor(Color.ORANGE);
				g.fillRect(odmikOdRoba + i*enotaDolzine, this.height - odmikOdRoba - (int)(delezi[i]*visinaGrafa), enotaDolzine, (int)(delezi[i]*visinaGrafa));
			
			}

		}else {

		 double vsota = 0;
		 for(double x : podatki) {
			 
		 	 vsota+=x;
		 }
		 double mera = 360./vsota;
		 int zacetniKot = 0;
		
		 for(int i = 0; i < podatki.length; i++) {
			 int kot = (int)Math.round(podatki[i]*mera);
			 if(i == podatki.length - 1) {
				 kot = 360 - zacetniKot;
			 }
			 g.setColor(colors[i]);
			 g.fillArc(this.x + this.width/2 - POLMER/2, this.y + this.height/2 - POLMER/2, POLMER, POLMER, zacetniKot, kot);
			 zacetniKot += kot;
			
		 }
		 /*
		  * fillArc(...., zacetniKot, kot) -> polni od zacetnegaKota za kot =>
		  * torej ne smemo notri dati kon�nega kota  
		  **/
		}
		
		
	}
	
	public void nastaviGumb(JButton button, Color color) {
			button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo širino
			button.setBackground(color); //nastavimo barvo
			button.setBorder(null); //znebimo se obrobe gumba
			button.setFocusable(false); //znebimo se obrobe button icon
	}
	
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
    }
	public int vrniX() {
		return this.x;
	}
	public int vrniY() {
		return this.y;
	}
	public int vrniWidth() {
		return this.width;
	}
	public int vrniHeight() {
		return this.height;
	}
	public JLabel vrniLabel() {
		return this.label;
	}


	public void setColors(Color[] colors) {
		this.colors = colors;
	}
	public Color[] getColors() {
		return this.colors;
	}
	public void setPodatki(double[] podatki) {
		this.podatki = podatki;
	}
	public double[] getPodatki() {
		return this.podatki;
	}
	public ArrayList<String> getKategorije(){
		return this.kategorije;
	}
	public void setKategorije(ArrayList<String> kategorije) {
		this.kategorije = kategorije;
	}
	/*
	 * Metoda, ki bo vrnila nabor med seboj dovolj razli�nih barv, ki bodo uporabljene
	 * na grafu
	 * */
	public Color[] naborBarv(int stBarv) {


		Color[] colors = new Color[stBarv];
		Random random = new Random();
		
		for(int i = 0; i < colors.length; i++) {
		
			Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
			boolean jePrava = true; //Za trackanje �e je barva prava ali ne
			
			for(int j = 0; j < i; j++) {
				if(Math.abs(color.getRed() - colors[j].getRed()) < 10 ||
						Math.abs(color.getBlue() - colors[j].getBlue()) < 10 ||
						Math.abs(color.getGreen() - colors[j].getGreen()) < 10) {


					i--;
					jePrava = false;
				}
			}
			if(jePrava == true) {

				colors[i] = color;
			}
		}

		return colors;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.comboBox) {
			if(this.comboBox.getSelectedItem() == "Income/Expense") {
				dobiIncomeExpensePodatke();
				
				if(this.histogram == true) {
					this.histogram = false;
					this.width -= this.vsebinskaPlosca.getLegenda().getWidth();
				}
				
				this.vsebinskaPlosca.getLegenda().removeAll();
				this.vsebinskaPlosca.getLegenda().setSteviloPolj(this.podatki.length); //ob novi .db druga�e ostanejo stari podatki (ArrayIndexOutOfBorder)
				this.vsebinskaPlosca.getLegenda().nastavi();
				this.vsebinskaPlosca.getLegenda().setVisible(true);
				this.revalidate();
				this.repaint();
				
			}else if(this.comboBox.getSelectedItem() == "Categories") {
				dobiPodatke();
				if(this.histogram == true) {
					this.histogram = false;
					this.width -= this.vsebinskaPlosca.getLegenda().getWidth();
				}
				this.vsebinskaPlosca.getLegenda().removeAll();
				this.vsebinskaPlosca.getLegenda().setSteviloPolj(this.podatki.length); //ob novi .db druga�e ostanejo stari podatki (ArrayIndexOutOfBorder)
				this.vsebinskaPlosca.getLegenda().nastavi();
				this.vsebinskaPlosca.getLegenda().setVisible(true);
				this.revalidate();
				this.repaint();
			}else if(this.comboBox.getSelectedItem() == "Expense per Month") {
				
				if(this.histogram == false) {
					this.width += this.vsebinskaPlosca.getLegenda().getWidth();
					this.histogram = true;
				}
				this.vsebinskaPlosca.getLegenda().removeAll();
				this.vsebinskaPlosca.getLegenda().setVisible(false);
				this.revalidate();
				this.repaint();
			}
		}
	}
	
}
