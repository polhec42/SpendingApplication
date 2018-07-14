import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LegendData extends JPanel{

	private final int RAZMERJE = 8;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color background;
	private Color data;
	private String text;
	
	private JLabel okenceBarva;
	private JLabel okenceTekst;
	
	private JPanel barvnica;
	
	public LegendData(int x, int y, int width, int height, Color background, Color data, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.background = background;
		this.data = data;
		this.text = text;
		
		LegendColorSquare legendColorSquare = new LegendColorSquare(
				0, 
				0, 
				3*this.width/RAZMERJE, 
				this.height, 
				this.background, 
				this.data
		);
		
		LegendaText legendaText = new LegendaText(
				legendColorSquare.getWidth(), 
				0, 
				this.width*5/RAZMERJE, 
				this.height, 
				this.background, 
				this.text
		);
		legendaText.setPreferredSize(new Dimension(this.width*5/RAZMERJE, this.height));
		legendColorSquare.setPreferredSize(new Dimension(this.width*3/RAZMERJE, this.height));
		this.add(legendColorSquare);
		this.add(legendaText);
		//this.setBorder(new LineBorder(Color.BLACK));
		/*Border empty = new EmptyBorder(10, 10, 10, 10);
		Border margin = new LineBorder(Color.BLACK);
		CompoundBorder compoundBorder = new CompoundBorder(empty, margin);
		this.setBorder(compoundBorder);
		*/
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
