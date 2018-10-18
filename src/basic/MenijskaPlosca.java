package basic;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MenijskaPlosca extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private VsebinskaPlosca vsebinskaPlosca;
	
	private JButton balance = null;
	private JButton add = null;
	private JButton graphs;
	private JButton settings;
	
	public MenijskaPlosca(int x, int y, int width, int height, Color color, VsebinskaPlosca vsebinskaPlosca) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vsebinskaPlosca = vsebinskaPlosca;
		//setPreferredSize(new Dimension(this.sirina, this.visina));
		setBackground(color);
		
		this.balance = new JButton(new ImageIcon("resources/euro-currency-symbol.png"));
        nastaviGumb(this.balance, color);
        //this.balance.setBackground(Color.LIGHT_GRAY);
		
		this.add = new JButton(new ImageIcon("resources/add.png"));
		nastaviGumb(this.add, color);
		
		this.graphs = new JButton(new ImageIcon("resources/investment.png"));
		nastaviGumb(this.graphs, color);
		
		this.settings = new JButton(new ImageIcon("resources/settings.png"));
		nastaviGumb(this.settings, color);
		 
		//potrebno je dodati actionListener -> ob pritisku se spremeni stanje
		//this.add = new JButton(new ImageIcon("resources/add.png"));
		
		//this.balance.addActionListener(this);
		//this.add.addActionListener(this);
		
	}
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width,70)); //nastavimo sirino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
		button.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.balance) {
			this.vsebinskaPlosca.setState(States.BALANCE);
		}else if(e.getSource() == this.add) {
			this.vsebinskaPlosca.setState(States.ADD);
		}else if(e.getSource() == this.graphs) {
			this.vsebinskaPlosca.setState(States.GRAPHS);
		}else if(e.getSource() == this.settings) {
			this.vsebinskaPlosca.setState(States.SETTINGS);
		}
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
	public JButton vrniBalance() {
		return this.balance;
	}
	public JButton vrniAdd() {
		return this.add;
	}
	public JButton vrniGraphs() {
		return this.graphs;
	}
	public JButton vrniSettings() {
		return this.settings;
	}
}
