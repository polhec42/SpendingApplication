import java.awt.*;
import javax.swing.*;

public class RisalnaPlosca extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public RisalnaPlosca(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		//setPreferredSize(new Dimension(this.sirina, this.visina));
		setBackground(color);
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
	
	
	
	
}
