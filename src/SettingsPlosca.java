import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class SettingsPlosca extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	private Test test;
	private NovaBaza  novaBaza;
	private PomoznoOkno pomoznoOkno;
	private VsebinskaPlosca vsebinskaPlosca;
	
	private JPanel databaseSettings;
	private JPanel appearanceSettings;
	private JPanel aboutPanel;
	private JTabbedPane tabbedPane;
	
	private String database = "Database Settings";
	private String appearance = "Appearance Settings";
	private String about = "About";
	
	private JLabel podatkiODatabase;
	private JButton novaDatabase;

	private JButton newColor;
	
	public SettingsPlosca(int x, int y, int width, int height, Color color, Test test, VsebinskaPlosca vsebinskaPlosca) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.test = test;
		this.color = color;
		this.vsebinskaPlosca = vsebinskaPlosca;
		//Tuki sta zaradi inicializacije in NullPointerja
		this.pomoznoOkno = new PomoznoOkno("New database");
		this.novaBaza = new NovaBaza(0, 0, this.pomoznoOkno.getWidth(), this.pomoznoOkno.getHeight(), this.color, this.test, this);

		setBackground(color);
		
		this.databaseSettings = new JPanel();
		this.appearanceSettings = new JPanel();
		this.aboutPanel = new JPanel();
		
		this.setLayout(new BorderLayout());
		/*
		 * Na TabbedPane das tabe, ki odpirajo JPanel
		 * */
		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.addTab(database, this.databaseSettings);
		this.tabbedPane.addTab(appearance, this.appearanceSettings);
		this.tabbedPane.addTab(about, this.aboutPanel);
		//this.mainPanel.add(this.tabbedPane, BorderLayout.CENTER);
		this.add(this.tabbedPane);
		
		onDatabaseSettings();
		onAboutSettings();
		onAppearanceSettings();
	}
	
	public void onDatabaseSettings() {
		this.databaseSettings.setLayout(new GridLayout(6,2));
		
		this.podatkiODatabase = new JLabel("Name of your database: " + this.test.getDatabaseName().toUpperCase(), SwingConstants.CENTER);
		this.novaDatabase = new JButton("New database");
		nastaviGumb(this.novaDatabase, this.color);
		
		this.databaseSettings.add(this.podatkiODatabase);
		this.databaseSettings.add(this.novaDatabase);
		
		for(int i = 0; i < 10; i++) {
			this.databaseSettings.add(new JLabel());
		}
	}
	public void onAboutSettings() {
		JLabel ime = new JLabel("Spending Application", SwingConstants.CENTER);
		JLabel avtor = new JLabel("Author: Žan Magerl", SwingConstants.CENTER);
		Init init = new Init();
		JLabel verzija = new JLabel(init.read("version"), SwingConstants.CENTER);
		
		this.aboutPanel.setLayout(new GridLayout(3, 1));
		this.aboutPanel.add(ime);
		this.aboutPanel.add(avtor);
		this.aboutPanel.add(verzija);
	}
	public void onAppearanceSettings() {
		JLabel background = new JLabel("Background color: ");
		this.newColor = new JButton("New color");
		nastaviGumb(newColor, this.color);
		
		
		this.appearanceSettings.setLayout(new GridLayout(5, 2));
		this.appearanceSettings.add(background);
		this.appearanceSettings.add(this.newColor);
		for(int i = 0; i < 8; i++) {
			this.appearanceSettings.add(new JLabel());
		}
	}
	
	public void nastaviGumb(JButton button, Color color) {
			button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo Å¡irino
			button.setBackground(color); //nastavimo barvo
			button.setBorder(null); //znebimo se obrobe gumba
			button.setFocusable(false); //znebimo se obrobe button icon
			button.addActionListener(this);
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
	public JLabel getPodatkiODatabase() {
		return podatkiODatabase;
	}
	public void setPodatkiODatabase(JLabel podatkiODatabase) {
		this.podatkiODatabase = podatkiODatabase;
	}
	public JButton getnovaDatabase() {
		return novaDatabase;
	}
	public void setnovaDatabase(JButton novaDatabase) {
		this.novaDatabase = novaDatabase;
	}
	public NovaBaza getNovaBaza() {
		return novaBaza;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.novaDatabase) {
			/*
			 * Zaèetek postopka kreiranja nove podatkovne baze
			 * */
			pomoznoOkno.add(novaBaza);
			pomoznoOkno.pack();
			pomoznoOkno.setVisible(true);
		}
		if(e.getSource() == this.newColor) {
			ColorPicker colorPicker = new ColorPicker(0, 0, width, height, color, this.vsebinskaPlosca);
			colorPicker.setPreferredSize(new Dimension(200, 300));
			PomoznoOkno pomoznoOkno2 = new PomoznoOkno("Color Picker");
			pomoznoOkno2.add(colorPicker);
			pomoznoOkno2.pack();
			pomoznoOkno2.setVisible(true);
		}
	}
	
}

