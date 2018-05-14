import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.synth.SynthInternalFrameUI;

public class BalancePlosca extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton button;
	private JLabel walletBalance;
	
	private Test test;
	
	public BalancePlosca(int x, int y, int width, int height, Color color, Test test) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.test = test;
		
		setBackground(color);
		this.button = new JButton("Deluje");
		//JLabel() ima tudi konstruktor, ki omogoèa aligment teksta
		this.walletBalance = new JLabel("Wallet Balance", SwingConstants.CENTER);
		izpisIzBaze();
	}
	
	public void izpisIzBaze() {
		Test.createNewTable();
    	
    	test.insert("Kosilo", "4.5.2018", "Wallet", 1.86, "Euro", "Hrana", "Expense");
    	test.insert("Zdravilo", "5.5.2018", "Wallet", 5.00, "Euro", "Zdavje", "Expense");
    	test.selectAll();
    	
    	double znesek = 0;
    	
    	ArrayList<Transakcija> list = test.vrniTransakcijeIzRacuna("Wallet");
    	for(int i = 0; i < list.size(); i++) {
    		znesek += list.get(i).getAmount();
    		System.out.printf("%s: %f\n", list.get(i).getDescription(), list.get(i).getAmount());
    	}
    	test.deleteTable("transactions");
    	
    	nastaviWalletBalance(Double.toString(znesek));
	}
	
	public void nastaviWalletBalance(String text) {
		this.walletBalance.setText(text);
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
	public JButton vrniButton() {
		return this.button;
	}
	public JLabel vrniWalletBalance() {
		return this.walletBalance;
	}
	
}
