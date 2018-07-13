import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
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
	
	private final int POLMER = 200;
	
	public GraphsPlosca(int x, int y, int width, int height, Color color, Test test) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.test = test;
		
		setBackground(color);				
	
		this.label = new JLabel("Text");
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		
		int[] podatki = {5, 12, 4, 16, 25};
		//Color[] colors = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.BLACK};
		Color[] colors = naborBarv(5);
		int vsota = 0;
		
		for(int x : podatki) {
			vsota+=x;
		}
		
		double mera = 360./vsota;
		
		int zacetniKot = 0;
		
		for(int i = 0; i < podatki.length; i++) {
			int kot = (int)Math.round(podatki[i]*mera);
			g.setColor(colors[i]);
			g.fillArc(this.x + this.width/2 - POLMER/2, this.y + this.height/2 - POLMER/2, POLMER, POLMER, zacetniKot, kot);
			zacetniKot += kot;
			
			System.out.println(i + " " + podatki[i] +  " " + zacetniKot + " " +  ((double)podatki[i]/vsota)*100 + " %");
		}
		/*
		 * fillArc(...., zacetniKot, kot) -> polni od zacetnegaKota za kot =>
		 * torej ne smemo notri dati konènega kota  
		 * */
	}
	
	public void nastaviGumb(JButton button, Color color) {
			button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo Å¡irino
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
	
	/*
	 * Metoda, ki bo vrnila nabor med seboj dovolj razliènih barv, ki bodo uporabljene
	 * na grafu
	 * */
	public Color[] naborBarv(int stBarv) {
		Color[] colors = new Color[stBarv];
		Random random = new Random();
		
		for(int i = 0; i < colors.length; i++) {
		
			Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
			boolean jePrava = true; //Za trackanje èe je barva prava ali ne
			
			for(int j = 0; j < i; j++) {
				if(Math.abs(color.getRed() - colors[j].getRed()) < 20 ||
						Math.abs(color.getBlue() - colors[j].getBlue()) < 20 ||
						Math.abs(color.getGreen() - colors[j].getGreen()) < 20) {
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
		// TODO Auto-generated method stub
	}
	
}
