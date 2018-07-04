import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PodrobnostiTransakcije extends JPanel{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JLabel label = new JLabel("To je polje");
	private JTable table;
	
	private Test test;
	
	public PodrobnostiTransakcije(int x, int y, int width, int height, Test test) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		
		String[] coloumnHeader = {"Atribut", "Opis"};
		Object[][] data = {
				{"Spol", "Moški"},
				{"Starost", "63"},
				{"Višina", "182"}
		};
		
		this.table = new JTable(data, coloumnHeader);
		
	}
	
	public JTable getTable() {
		return this.table;
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
	
	public JLabel getLabel() {
		return this.label;
	}
	
	
	
	
}
