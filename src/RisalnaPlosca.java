import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RisalnaPlosca extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton balance;
	
	public RisalnaPlosca(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		//setPreferredSize(new Dimension(this.sirina, this.visina));
		setBackground(color);
		
		this.balance = new JButton(new ImageIcon("resources/euro-currency-symbol.png"));
		this.balance.setPreferredSize(new Dimension(this.width,70)); //nastavimo širino
		this.balance.setBackground(color); //nastavimo barvo
		this.balance.setBorder(null); //znebimo se obrobe gumba
		this.balance.setFocusable(false); //znebimo se obrobe button icon
		
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
	
	
	
	
}
