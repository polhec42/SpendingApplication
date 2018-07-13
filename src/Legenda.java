import java.awt.Color;

import javax.swing.JPanel;

public class Legenda extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	public Legenda(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
}
