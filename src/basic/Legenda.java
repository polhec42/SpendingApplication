package basic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Legenda extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private int steviloPolj;
	
	private GraphsPlosca graphsPlosca;
	
	public Legenda(int x, int y, int width, int height, Color color, int steviloPolj, GraphsPlosca graphsPlosca) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.steviloPolj = steviloPolj;
		this.graphsPlosca = graphsPlosca;
		
		setBackground(this.color);
		nastavi();
	}
	/*
	 * Poskusna metoda za legendo
	 * */
	public void nastavi() {
		/*
		 * Nastavitev Layouta Legende -> da se njen Layout in razporeditev dinamièno
		 * spreminja z številom podatkov
		 * */
		int stVrstic = this.steviloPolj >= 8 ? 8 : this.steviloPolj % 8;
		int stStolpcev = this.steviloPolj/8 + 1;
		
		this.setLayout(new GridLayout(stVrstic, stStolpcev));
		
		Color[] barve = this.graphsPlosca.getColors();
		double[] podatki = this.graphsPlosca.getPodatki();
		
		LegendData[] legendDatas = new LegendData[this.steviloPolj];

		for(int i = 0; i < this.steviloPolj; i++) {			
			/*
			 * Dodajati moram "0 + i*this.height/stVrstic", ker se drugaèe preslikajo na isto mesto
			 * saj to niso standardizirane Swing komponente
			 * */
			legendDatas[i] = new LegendData(0, 0 + i*this.height/stVrstic, this.width/stStolpcev, this.height/stVrstic, this.color, barve[i], this.graphsPlosca.getKategorije().get(i));
			this.add(legendDatas[i]);
		}

	}
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
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
