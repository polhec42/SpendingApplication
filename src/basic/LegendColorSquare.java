package basic;
import java.awt.Color;
import java.awt.Graphics;

import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LegendColorSquare extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Color background;
	private Color data;

	public LegendColorSquare(int x, int y, int width, int height, Color background, Color data) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
		this.data = data;
	}
	
	private int smaller() {
		return (this.width > this.height) ? this.height : this.width;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(this.data);
		//g.fillRect(this.width/4 - smaller()/4, this.height/4 + this.height/8, smaller()/2, smaller()/2);
		//g.fillRect(this.width/3, this.height/3, smaller()/2, smaller()/2);
		/*
		 * To razvij 
		 */
		if(height > width) {
			g.fillRect(this.width/4, (this.height - this.width/2) / 2, width/2, width/2);
		}else {
			g.fillRect((this.width - this.height/2) / 2, this.height/4, height/2, height/2);
	
		}
	}

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

	public Color getData() {
		return data;
	}

	public void setData(Color data) {
		this.data = data;
	}
}
