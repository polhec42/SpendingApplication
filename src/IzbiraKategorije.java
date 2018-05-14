import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class IzbiraKategorije extends JPanel implements ListSelectionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton button;
	private JList<String> area;
	private JLabel label;
	
	private JScrollPane vertical;
	
	private Test test;
	
	public IzbiraKategorije(int x, int y, int width, int height, Test test) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		
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
		
		ArrayList<String> kategorije = test.vrniKategorije();
		System.out.println(kategorije);
		/*
		String[] data = {"one", "two", "three", "four"};
		*/
		String[] data = new String[kategorije.size()];
		for(int i = 0; i < kategorije.size(); i++) {
			data[i] = kategorije.get(i);
		}
		this.area = new JList<String>(data);
		
		this.vertical = new JScrollPane(area);
		vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		area.addListSelectionListener(this);
	}
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
    }
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo širino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
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
	        label.setText(this.area.getSelectedValue().toString());
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
	
}
