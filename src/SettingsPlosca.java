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
	
	private JPanel databaseSettings;
	private JPanel appearanceSettings;
	private JPanel aboutPanel;
	private JTabbedPane tabbedPane;
	
	private String database = "Database Settings";
	private String appearance = "Appearance Settings";
	private String about = "About";
	
	private JLabel podatkiODatabase;
	private JButton novaDatabase;

	public SettingsPlosca(int x, int y, int width, int height, Color color, Test test) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.test = test;
		this.color = color;
		
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
		this.tabbedPane.addTab(about, aboutPanel);
		//this.mainPanel.add(this.tabbedPane, BorderLayout.CENTER);
		this.add(this.tabbedPane);
		this.setBorder(new LineBorder(Color.GREEN));
		
		onDatabaseSettings();
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
	}
	
}

