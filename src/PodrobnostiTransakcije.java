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
	
	public PodrobnostiTransakcije(int x, int y, int width, int height, Test test, int selectedIndex, String transactionCategory, SeznamTransakcij seznamTrasakcij) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		this.seznamTransakcij = seznamTrasakcij;
		
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
				{"Cena", transakcije.get(selectedIndex).getAmount()},
				{"Kategorija", transakcije.get(selectedIndex).getCategory()},
				{"Tip", transakcije.get(selectedIndex).getType()}
		};
		this.id = transakcije.get(selectedIndex).getId();
		
		
		this.table = new JTable(data, coloumnHeader);
		
		this.edit = new JButton("Enter");
		nastaviGumb(this.edit, Color.WHITE);
		this.edit.addActionListener(this);
	}
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/4,70)); //nastavimo Å¡irino
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
			 * Table shrani to kar si uredil šele takrat
			 * ko nehaš urejati celice v tabeli.
			 * -> ker Table ne ve kdaj bomo nehali urejati
			 * mu moramo to mi povedati.
			 * */
			if(table.isEditing()) {
				table.getCellEditor().stopCellEditing();
				int column = 1;
				this.test.update(this.id, (String)table.getValueAt(0, column), 
						(String)table.getValueAt(1, column), (String)table.getValueAt(2, column),
						(String)(table.getValueAt(3, column)),
						"Euro", (String)table.getValueAt(4, column), (String)table.getValueAt(5, column));
				
				//Posodobim seznam transakcij
				this.seznamTransakcij.posodobiTransakcije();
			}		
		}
		
	}
	
	
	
}
