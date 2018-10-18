import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class IzbiraKategorije extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private ArrayList<String> kategorije;
	
	private JButton button;
	private JList<String> area;
	private JLabel label;
	private JTextField novaKategorija;
	
	private JTable tabelaKategorij;
	private DefaultTableModel tableModel;
	
	private JScrollPane vertical;
	
	private Test test;
	
	private AddPlosca addPlosca;
	
	public IzbiraKategorije(int x, int y, int width, int height, Test test, AddPlosca addPlosca) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		this.addPlosca = addPlosca;
		
		this.label = new JLabel("Empty", SwingConstants.CENTER);
		this.label.setPreferredSize(new Dimension(this.width, 50));
		
		this.button = new JButton("Enter");
		this.nastaviGumb(this.button, Color.LIGHT_GRAY);
		/*
		 * Testing ground for categories
		 * */
		//Test.createTableCategory();
		//test.newCategory("Food");
		//test.newCategory("Gift");
		//test.newCategory("Technology");
		
		this.kategorije = test.vrniKategorije();
		System.out.println(this.kategorije);
		
		Object[][] data = new Object[this.kategorije.size()][1];
		
		for(int i = 0; i < this.kategorije.size(); i++) {
			Object[] object = {kategorije.get(i)};
			data[i] = object;
		}
		System.out.println(Arrays.deepToString(data));

		
		this.tableModel = new DefaultTableModel(data, new String[]{"Category"});
		
		this.tabelaKategorij = new JTable(this.tableModel);
		this.tabelaKategorij.setShowGrid(false);
		
		
		this.novaKategorija = new JTextField(20);
		
		this.button.addActionListener(this);
		this.novaKategorija.addActionListener(this);
	}
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
    }
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo Å¡irino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.button) {
			/*
			 * To trenutno doda kategorijo med možnosti dropdown menija in jo tudi izbere
			 * Uporabno bo, ko bom dodal možnost, da uporabnik dodaja svoje kategorije
			 * - pri tem moram poskrbeti da se nova kategorija zapiše v bazo
			 */
			if(!this.novaKategorija.getText().isEmpty()) {
				this.addPlosca.vrniCategoriesList().setText(this.novaKategorija.getText());
			}else {
				this.novaKategorija.setText(this.tabelaKategorij.getValueAt(this.tabelaKategorij.getSelectedRow(), 0).toString());
				this.addPlosca.vrniCategoriesList().setText(this.novaKategorija.getText());
			}
			
			boolean zeObstaja = false;
			for(int i = 0; i < this.kategorije.size(); i++) {
				if(this.kategorije.get(i).equals(this.novaKategorija.getText())) {
					zeObstaja = true;
					break;
				}
			}
			if(!zeObstaja) {
				this.test.newCategory(this.novaKategorija.getText());
			}
							
			ArrayList<String> c = new ArrayList<>();
			for(int i = 0; i < this.tabelaKategorij.getRowCount(); i++) {
				c.add((String)this.tabelaKategorij.getValueAt(i, 0));
			}
			this.test.updateCategories(c);
			//gremo èez vse transakcije in spremenimo kategorije
			for(int i = 0; i < kategorije.size(); i++) {
				if(!kategorije.get(i).equals(c.get(i))) {
					ArrayList<Transakcija> vseTransakcijeTeKategorije = test.vrniTransakcijeIzKategorije(kategorije.get(i));
					for(int j = 0; j < vseTransakcijeTeKategorije.size(); j++) {
						this.test.update(vseTransakcijeTeKategorije.get(j).getId(), 
								vseTransakcijeTeKategorije.get(j).getDescription(), 
								vseTransakcijeTeKategorije.get(j).getDate(), 
								vseTransakcijeTeKategorije.get(j).getAccount(), 
								Double.toString(vseTransakcijeTeKategorije.get(j).getAmount()), 
								vseTransakcijeTeKategorije.get(j).getCurrency(), 									c.get(i), 
								vseTransakcijeTeKategorije.get(j).getType());
					}
				}
			}
				
			this.kategorije = this.test.vrniKategorije();
			for(int i = 0; i < this.kategorije.size(); i++) {
				if(this.kategorije.get(i).equals("")) {
					this.kategorije.remove(i);
					this.test.deleteData("categories", i+1);
				}
			}
		}
		
		
		
	}
	
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
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
	
	public JButton vrniButton() {
		return this.button;
	}
	public JList vrniArea() {
		return this.area;
	}
	public JTable vrniTable() {
		return this.tabelaKategorij;
	}
	public JTextField vrniTextField() {
		return this.novaKategorija;
	}
	
	
}
