package panels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.Test;
import database.Transakcija;

public class PodrobnostiTransakcije extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int id;
	
	private JTable table;
	private JButton edit;
	
	private DefaultTableModel tableModel;
	
	private Test test;
	private SeznamTransakcij seznamTransakcij;
	
	private Transakcija transakcija;
	
	public PodrobnostiTransakcije(int x, int y, int width, int height, Test test, Transakcija transakcija,  SeznamTransakcij seznamTrasakcij) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		this.transakcija = transakcija;
		this.seznamTransakcij = seznamTrasakcij;
		
		String[] coloumnHeader = {"Atribut", "Vrednost"};
		
		/*
		 * selectedIndex = indeks izbrane transakcije
		 * transactionCategory = kategorija, po kateri smo pri seznamu 
		 * 						v Balance oknu filtrirali transakcije
		 * To dvoje rabim, da lahko sedaj prika�em in umestim podrobnost transakcije
		 * */
		
		Object[][] data = {
				{"Opis", transakcija.getDescription()},
				{"Datum", transakcija.getDate()},
				{"Ra�un", transakcija.getAccount()},
				{"Cena", transakcija.getAmount()},
				{"Kategorija", transakcija.getCategory()},
				{"Tip", transakcija.getType()}
		};
		this.id = transakcija.getId();
		
		
		this.table = new JTable(data, coloumnHeader);
		
		this.edit = new JButton("Enter");
		nastaviGumb(this.edit, Color.WHITE);
		this.edit.addActionListener(this);
	}
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/4,70)); //nastavimo širino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
		button.setOpaque(true);
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
	
	public JButton getEditButton() {
		return this.edit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if(e.getSource() == this.edit) {
			
			/*
			 * Table shrani to kar si uredil �ele takrat
			 * ko neha� urejati celice v tabeli.
			 * -> ker Table ne ve kdaj bomo nehali urejati
			 * mu moramo to mi povedati.
			 * */
			if(table.isEditing()) {
				table.getCellEditor().stopCellEditing();
				int column = 1;
				this.test.update(this.id, (String)table.getValueAt(0, column), 
						(String)table.getValueAt(1, column), (String)table.getValueAt(2, column),
						checkStringOrDouble((table.getValueAt(3, column))),
						"Euro", (String)table.getValueAt(4, column), (String)table.getValueAt(5, column));
				
				//Posodobim seznam transakcij
				this.seznamTransakcij.posodobiTransakcije();
				
			}		
		}
		
	}
	private String checkStringOrDouble(Object object) {

		if(object instanceof Double) {
			return Double.toString((double)object);
		}else {
			return (String)object;
		}
	}
	
	
}
