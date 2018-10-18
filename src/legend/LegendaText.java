package legend;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class LegendaText extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Color background;
	private String data;
	private JLabel text;
	
	public LegendaText(int x, int y, int width, int height, Color background, String data) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
		this.data = data;
		
		this.text = new JLabel(this.data);
		this.setLayout(new GridLayout(1,1));
		this.text.setHorizontalAlignment(SwingConstants.CENTER);
		this.text.setVerticalAlignment(SwingConstants.CENTER);
		this.add(this.text);
		
	}
	
	

	/*
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(this.data, this.x, this.height/4);
	}
	*/
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public Color getBackground() {
		return background;
	}


	public void setBackground(Color background) {
		this.background = background;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
