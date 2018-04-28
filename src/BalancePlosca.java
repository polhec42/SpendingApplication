import java.awt.Color;

import javax.swing.*;

public class BalancePlosca extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton button;
	
	public BalancePlosca(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		
		setBackground(color);
		this.button = new JButton("Deluje");
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
	
}
