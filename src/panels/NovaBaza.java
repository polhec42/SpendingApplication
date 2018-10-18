package panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
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

import basic.Init;
import database.Test;


public class NovaBaza extends JPanel implements ActionListener, ListSelectionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
		
	private ArrayList<String> databases;
	
	private Test test;
	private SettingsPlosca settingsPlosca;
	
	private JTextField vnosnoPolje;
	private JButton potrdi;
	private JList area;
	private JScrollPane vertical;
	private DefaultListModel listModel;
	
	private File folder;
	
	public NovaBaza(int x, int y, int width, int height, Color color, Test test, SettingsPlosca settingsPlosca) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		this.color = color;
		this.settingsPlosca = settingsPlosca; 
		
		this.setBackground(color);
		
		this.databases = new ArrayList();
		
		this.folder = new File("resources");
		listFilesForFolder(folder);
		
		dodajElemente();
	}

	public void dodajElemente() {
		
		this.vnosnoPolje = new JTextField();
		this.potrdi = new JButton("Submit");
		nastaviGumb(this.potrdi, this.color);
		
		
		this.listModel = new DefaultListModel();
		
		for(int i = 0; i < databases.size(); i++) {
			this.listModel.addElement(databases.get(i));
		}
		this.area = new JList<String>(this.listModel);
		this.vertical = new JScrollPane(area);
		vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.area.addListSelectionListener(this);
		
		this.setLayout(new BorderLayout());
		
		this.add(this.vnosnoPolje, BorderLayout.NORTH);
		this.add(this.area, BorderLayout.CENTER);
		this.add(this.potrdi, BorderLayout.SOUTH);
	}
	public void posodobiDatabases() {
		
		this.databases.clear();
		listFilesForFolder(folder);
		//Spraznimo ves data in ga znova spišimo
		//Removam ListSelectionListener, ker se drugaèe ob 
		//posodobitvi sproži in je en element null
		//To je quick fix -> enkrat bolj natanèno preglej
		this.area.removeListSelectionListener(this);
		this.listModel.removeAllElements();
		
		for(int i = 0; i < this.databases.size(); i++) {
			this.listModel.addElement(this.databases.get(i));
		}
		this.area.addListSelectionListener(this);

	}
	
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
    }
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo Å¡irino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
		button.addActionListener(this);
	}
	/*
	 * Funkcija, ki prebere vse datoteke v mapi
	 * in izbere tiste, ki se podatkovne baze
	 * */
	public void listFilesForFolder(File folder) {

		try {			
			for (File file : folder.listFiles()) {
				if (file.isFile()) {
					if(file.getName().contains(".db")) {
						/*
						 * Zaradi komplikacij zaradi novih baz in
						 * obstojeèih, je najbolje, da se pusti brez .db
						 * */
						String name = file.getName().replace(".db", "");
						this.databases.add(name);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
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
			
			this.settingsPlosca.getPodatkiODatabase().setText(novoIme.toUpperCase());
			this.test.setDatabaseName(novoIme);
			
			for(String name : databases) {
				if(name.concat(".db").equals(novoIme)) {
					//Èe ta baza že obstaja, potem ne ustvarimo nove
					return;
				}
			}
			
			this.test.createNewDatabase(novoIme);
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
	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {
	        this.vnosnoPolje.setText(this.area.getSelectedValue().toString());
	    }

	}
	
}
