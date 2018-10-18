package basic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.SkeletonNotFoundException;
import java.util.Arrays;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class ColorPicker extends JPanel{
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	
	private Color[] colors;
	
	private VsebinskaPlosca vsebinskaPlosca;
	
	private final ColorPicker self = this;
	
	private JPanel barve;
	
	public ColorPicker(int x, int y, int width, int height, Color color, VsebinskaPlosca vsebinskaPlosca) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.vsebinskaPlosca = vsebinskaPlosca;
		
		this.colors = new Color[]{Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
				Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
				Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
		dodajElemente();
	}
	
	public void dodajElemente() {
		
		this.barve = new JPanel();
		JPanel gumb = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		barve.setLayout(new GridLayout(3, 4));
		for(int i = 0; i < this.colors.length; i++) {
			JButton jLabel= new JButton();
			nastaviGumb(jLabel, this.colors[i]);
			barve.add(jLabel);
			
		}
		
		JButton button = new JButton("Enter");
		nastaviGumb(button, this.color);
		gumb.add(button);
		
		this.add(barve, BorderLayout.CENTER);
		this.add(gumb, BorderLayout.SOUTH);
		
	}
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo Å¡irino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
		
		//To implementiram kar sem, ker imam preveè gumbov, da
		//bi preverjal za vsakega posebej
		button.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Prvi action se zgodi samo èe pritisnemo na barve in ne 
				 * na potrdi gumb - (getParent() metoda vrne Container komponente)
				 * */
				if(e.getSource() == button && button.getParent() == self.barve) {
					
					/*
					 * Metoda, ki omogoèi, da se ob kliku na drugo barvo
					 * na prejšni barvi izbriše border in se nastavi na novem
					 * To se doseže tako da se sprehodimo èez vse komponente 
					 * tega panela in mu zbrišemo borders.
					 * */
					
					for(Component component : self.barve.getComponents()) {
						if(component instanceof JComponent) {
							((JComponent) component).setBorder(null);
						}
						
					}
					
					button.setBorder(new LineBorder(Color.BLACK, 2));	
				}
				/*
				 * Ta del poskrbi, da dobimo id barve, ki jo je
				 * uporabnik izbral -> sproži se ko pritisnemo Enter
				 * */
				else if(e.getSource() == button) {
					int stevec = 0;
					for(Component component : self.barve.getComponents()) {
						if(component instanceof JComponent) {
							//getBorder()deluje zgolj nad JComponent-ami
							JComponent component2 = (JComponent)component;
							if(component2.getBorder() != null){
								/*
								 * Uporabnikovo izbiro zapišemo v podatke
								 * in spremenimo ozadje
								 * */
								Init init = new Init();
								init.modify("background-color", String.valueOf(colors[stevec].getRGB()));
								
								/*V nastajanju: sprememba barve vseh komponent
								 * */
								Color beColor = colors[stevec];	
								for(Component c : self.vsebinskaPlosca.getComponents()) {
									
									c.setBackground(beColor);
									
								}
								self.vsebinskaPlosca.setBackground(beColor);
								
							}
						}
						stevec++;
					}
				}
			}
		});
	
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

	
	
	
	
}
