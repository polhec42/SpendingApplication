import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class IzbiraKategorije extends JPanel implements ListSelectionListener, ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private ArrayList<String> kategorije;
	
	private JButton button;
	private JList<String> area;
	private JLabel label;
	private JTextField novaKategorija;
	
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
		
		this.button = new JButton("Gumb");
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
		
		String[] data = new String[this.kategorije.size()];
		for(int i = 0; i < this.kategorije.size(); i++) {
			data[i] = this.kategorije.get(i);
		}
		
		this.area = new JList<String>(data);
		this.area.setPreferredSize(new Dimension(this.width, 60));
		this.vertical = new JScrollPane(area);
		vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.novaKategorija = new JTextField(20);
		
		area.addListSelectionListener(this);
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
			 * */
			this.addPlosca.vrniCategoriesList().addItem(this.novaKategorija.getText());
			this.addPlosca.vrniCategoriesList().setSelectedItem(this.novaKategorija.getText());
			
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

			//dodaj funkcionalnosti za dodajanje kategorije v bazo
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (this.area.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            this.button.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            this.button.setEnabled(true);
	        }
	        //Ko uporabnik izbere možnost, se ta enostavno zapiše v JTextField
	        novaKategorija.setText(this.area.getSelectedValue().toString());
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
	public JScrollPane vrniScroll() {
		return this.vertical;
	}
	public JTextField vrniTextField() {
		return this.novaKategorija;
	}
	
	
}
