package basic;
import java.awt.*;

import javax.swing.JFrame;

public class Okno extends JFrame{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Okno(String ime) {
		setTitle(ime);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		
		int dSirina = d.width;
		int dVisina = d.height;
		
		setX(dSirina/5);
		setY(dVisina/5);
		setWidth(2*dSirina/3);
		setHeight(2*dVisina/3);
		
		setBounds(x, y, width, height);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
}
