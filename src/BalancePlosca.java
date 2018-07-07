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
	private JLabel bankBalance;
	private JLabel cryptoBalance;
	
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
		this.bankBalance = new JLabel("Bank Balance", SwingConstants.CENTER);
		this.cryptoBalance = new JLabel("Crypto Balance", SwingConstants.CENTER);
		izpisIzBaze();
	}
	
	public void izpisIzBaze() {
		/*
    	test.insert("Kosilo", "4.5.2018", "Wallet", 1.86, "Euro", "Hrana", "Expense");
    	test.insert("Zdravilo", "5.5.2018", "Wallet", 5.00, "Euro", "Zdavje", "Expense");
    	*/
    	double znesekWallet = 0;
    	
    	ArrayList<Transakcija> list = test.vrniTransakcijeIzRacuna("Wallet");
    	for(int i = 0; i < list.size(); i++) {
    		if(list.get(i).getType().equals("Income")) {
    			znesekWallet += list.get(i).getAmount();
        		System.out.printf("Income: %s: %f\n", list.get(i).getDescription(), list.get(i).getAmount());
    		}else if(list.get(i).getType().equals("Expense")) {
    			znesekWallet -= list.get(i).getAmount();
        		System.out.printf("Expense: %s: %f\n", list.get(i).getDescription(), list.get(i).getAmount());
    		}
    	}
    	nastaviWalletBalance(Double.toString(znesekWallet));
    	
    	double znesekBank = 0;
    	ArrayList<Transakcija> listBank = test.vrniTransakcijeIzRacuna("Bank");
    	for(int i = 0; i < listBank.size(); i++) {
    		if(listBank.get(i).getType().equals("Income")) {
    			znesekBank += listBank.get(i).getAmount();
        		System.out.printf("Income: %s: %f\n", listBank.get(i).getDescription(), listBank.get(i).getAmount());
    		}else if(listBank.get(i).getType().equals("Expense")) {
    			znesekBank -= listBank.get(i).getAmount();
        		System.out.printf("Expense: %s: %f\n", listBank.get(i).getDescription(), listBank.get(i).getAmount());
    		}
    	}
    	nastaviBankBalance(Double.toString(znesekBank));
	}
	
	public void nastaviWalletBalance(String text) {
		this.walletBalance.setText("Wallet: " + text + " €");
	}
	public void nastaviBankBalance(String text) {
		this.bankBalance.setText("Bank: " + text + " €");
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
	public JLabel vrniBankBalance() {
		return this.bankBalance;
	}
	public JLabel vrniCryptoBalance() {
		return this.cryptoBalance;
	}
	
}
