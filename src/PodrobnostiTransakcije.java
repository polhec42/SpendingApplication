import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PodrobnostiTransakcije extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;

	private JTable table;
	
	private Test test;
	
	public PodrobnostiTransakcije(int x, int y, int width, int height, Test test, int selectedIndex, String transactionCategory) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		
		String[] coloumnHeader = {"Atribut", "Vrednost"};
		
		/*
		 * selectedIndex = indeks izbrane transakcije
		 * transactionCategory = kategorija, po kateri smo pri seznamu 
		 * 						v Balance oknu filtrirali transakcije
		 * To dvoje rabim, da lahko sedaj prikažem in umestim podrobnost transakcije
		 * */
		ArrayList<Transakcija> transakcije = test.vrniTransakcijeIzRacuna(transactionCategory);
		
		Object[][] data = {
				{"Opis", transakcije.get(selectedIndex).getDescription()},
				{"Datum", transakcije.get(selectedIndex).getDate()},
				{"Raèun", transakcije.get(selectedIndex).getAccount()},
				{"Kolièina", transakcije.get(selectedIndex).getAmount() + " " + transakcije.get(selectedIndex).getCurrency()},
				{"Kategorija", transakcije.get(selectedIndex).getCategory()},
				{"Tip", transakcije.get(selectedIndex).getType()}
		};
		
		this.table = new JTable(data, coloumnHeader);
		
	}
	
	public JTable getTable() {
		return this.table;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
