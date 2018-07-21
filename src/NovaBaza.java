import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class NovaBaza extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	private Test test;
	private SettingsPlosca settingsPlosca;
	
	private JTextField vnosnoPolje;
	private JButton potrdi;
	
	public NovaBaza(int x, int y, int width, int height, Color color, Test test, SettingsPlosca settingsPlosca) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		this.color = color;
		this.settingsPlosca = settingsPlosca; 
		
		this.setBackground(color);
		
		dodajElemente();
	}

	public void dodajElemente() {
		
		this.vnosnoPolje = new JTextField();
		this.potrdi = new JButton("Submit");
		nastaviGumb(this.potrdi, this.color);
		
		this.setLayout(new BorderLayout());
		
		this.add(this.vnosnoPolje, BorderLayout.NORTH);
		this.add(this.potrdi, BorderLayout.SOUTH);
	}
	
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
    }
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo širino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
		button.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.potrdi && !this.vnosnoPolje.equals("") && !this.vnosnoPolje.equals(" ")) {
			
			String novoIme = this.vnosnoPolje.getText().concat(".db");
			
			/*
			 * V datoteki config spremenimo zapis za ime baze
			 * */
			Init init = new Init();
			init.modify("database", novoIme);
			
			this.test.createNewDatabase(novoIme);
			this.settingsPlosca.getPodatkiODatabase().setText(novoIme.toUpperCase());
			this.test.setDatabaseName(novoIme);
			
			this.test.createNewTableTransactions();
			this.test.createNewTableCategories();
		}
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
