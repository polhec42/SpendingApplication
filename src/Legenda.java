import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Legenda extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private int steviloPolj;
	
	public Legenda(int x, int y, int width, int height, Color color, int steviloPolj) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.steviloPolj = steviloPolj;
		
		setBackground(this.color);
		nastavi();
	}
	/*
	 * Poskusna metoda za legendo
	 * */
	public void nastavi() {
		for(int i = 0; i < this.steviloPolj; i++) {
			JLabel jLabel= new JLabel(Integer.toString(i));
			this.add(jLabel);
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSteviloPolj() {
		return steviloPolj;
	}

	public void setSteviloPolj(int steviloPolj) {
		this.steviloPolj = steviloPolj;
	}
	
}
