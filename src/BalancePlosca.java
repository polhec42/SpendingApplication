import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.synth.SynthInternalFrameUI;

public class BalancePlosca extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton button;
	private JButton walletBalance;
	private JButton bankBalance;
	private JButton cryptoBalance;
	
	private Test test;
	private SeznamTransakcij seznamTransakcij;
	
	public BalancePlosca(int x, int y, int width, int height, Color color, Test test, SeznamTransakcij seznamTransakcij) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.test = test;
		this.seznamTransakcij = seznamTransakcij;
		
		setBackground(color);
		this.button = new JButton("Deluje");
		//JLabel() ima tudi konstruktor, ki omogo�a aligment teksta
		this.walletBalance = new JButton("Wallet Balance");
		this.bankBalance = new JButton("Bank Balance");
		this.cryptoBalance = new JButton("Crypto Balance");
		
		nastaviGumb(this.walletBalance, color);
		nastaviGumb(this.bankBalance, color);
		nastaviGumb(this.cryptoBalance, color);
		
		izpisIzBaze();
		
		this.walletBalance.addActionListener(this);
		this.bankBalance.addActionListener(this);
		this.cryptoBalance.addActionListener(this);
	}
	
	 public void nastaviGumb(JButton button, Color color) {
			button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo širino
			button.setBackground(color); //nastavimo barvo
			button.setBorder(null); //znebimo se obrobe gumba
			button.setFocusable(false); //znebimo se obrobe button icon
			//button.addActionListener(this);
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
		this.walletBalance.setText("Wallet: " + text + " �");
	}
	public void nastaviBankBalance(String text) {
		this.bankBalance.setText("Bank: " + text + " �");
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
	public JButton vrniWalletBalance() {
		return this.walletBalance;
	}
	public JButton vrniBankBalance() {
		return this.bankBalance;
	}
	public JButton vrniCryptoBalance() {
		return this.cryptoBalance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.walletBalance) {
			LineBorder border = new LineBorder(Color.RED, 2);
			this.walletBalance.setBorder(border);
			this.walletBalance.setBorderPainted(true);
			
			this.bankBalance.setBorderPainted(false);
			this.cryptoBalance.setBorderPainted(false);
			
			this.seznamTransakcij.setTransactionCategory("Wallet");
			this.seznamTransakcij.posodobiTransakcije();
		}
		if(e.getSource() == this.bankBalance) {
			LineBorder border = new LineBorder(Color.RED, 2);
			this.bankBalance.setBorder(border);
			this.bankBalance.setBorderPainted(true);
			
			this.walletBalance.setBorderPainted(false);
			this.cryptoBalance.setBorderPainted(false);
			
			this.seznamTransakcij.setTransactionCategory("Bank");
			this.seznamTransakcij.posodobiTransakcije();
		}
		if(e.getSource() == this.cryptoBalance) {
			LineBorder border = new LineBorder(Color.RED, 2);
			this.cryptoBalance.setBorder(border);
			this.cryptoBalance.setBorderPainted(true);

			this.walletBalance.setBorderPainted(false);
			this.bankBalance.setBorderPainted(false);
			
			this.seznamTransakcij.setTransactionCategory("Crypto");
			this.seznamTransakcij.posodobiTransakcije();

		}
	}
	
}
