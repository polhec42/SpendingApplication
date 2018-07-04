import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class VsebinskaPlosca extends JPanel implements Runnable{
	
	public final VsebinskaPlosca self = this;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton balance;
	
	States state;
	
	private boolean running;
	
	private BalancePlosca balancePlosca;
    private AddPlosca addPlosca;
	private SeznamTransakcij seznamTransakcij;
    private JPanel panel;
    
	private Okno okno;
	private Test test;
	
	public VsebinskaPlosca(int x, int y, int width, int height, Color color, Okno okno, Test test) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.okno = okno;
		this.test = test;
		
		setBackground(color);
				
		this.state = States.BALANCE;
		/*
		 * Dodajanje BalancePlosca na vsebinsko plosco.
		 * 
		 * 
		 */
		this.balancePlosca = new BalancePlosca(this.x, this.y, this.width, this.height, color, this.test);
		this.balancePlosca.nastaviVelikost(this.width/2, this.height);
		
		this.balancePlosca.setLayout(new GridLayout(6, 2));
		this.balancePlosca.add(this.balancePlosca.vrniButton());
		this.balancePlosca.add(new JLabel());
		this.balancePlosca.add(this.balancePlosca.vrniWalletBalance());
		this.balancePlosca.add(new JLabel());
		this.balancePlosca.add(this.balancePlosca.vrniBankBalance());
		this.balancePlosca.add(new JLabel());
		this.balancePlosca.add(this.balancePlosca.vrniCryptoBalance());

		for(int i = 0; i < 5; i++) {
	        	this.balancePlosca.add(new JLabel());
	    }
		/*
		 * Test za Layoute
		 * 
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(this.width/2, this.height));
		this.panel.setBorder(BorderFactory.createLineBorder(Color.RED));
		*/
		//nevem zakaj moram vi�ino pove�at za 35 -> I need to check this later
		this.seznamTransakcij = new SeznamTransakcij(this.x + this.width/6 - 10, 10, this.width/2, this.height, this.test);
		this.seznamTransakcij.nastaviVelikost(this.width/2, this.height);
		
		this.seznamTransakcij.setLayout(new BorderLayout());
		this.seznamTransakcij.add(this.seznamTransakcij.vrniIzberiButton(), BorderLayout.SOUTH);

		this.seznamTransakcij.setBorder(BorderFactory.createLineBorder(Color.RED));
		Border border = seznamTransakcij.getBorder();
	    Border margin = new EmptyBorder(10,10,10,10);
	    seznamTransakcij.setBorder(new CompoundBorder(border, margin));
		this.seznamTransakcij.add(this.seznamTransakcij.vrniScroll(), BorderLayout.CENTER);
		this.seznamTransakcij.add(this.seznamTransakcij.vrniLabel(), BorderLayout.NORTH);
		
		this.addPlosca = new AddPlosca(this.x, this.y, this.width, this.height, color, okno, this.test);
        this.addPlosca.nastaviVelikost(this.width, this.height);
        this.addPlosca.setLayout(new GridLayout(6, 2));
        this.addPlosca.add(this.addPlosca.vrniExpenseButton());
        this.addPlosca.add(this.addPlosca.vrniIncomeButton());
        this.addPlosca.add(this.addPlosca.vrniList());
        this.addPlosca.add(this.addPlosca.vrniCategoryButton());
        //This chunk of code is used to fill addPlosca with empty elements
        //it has fixed the layout issues
        for(int i = 0; i < 8; i++) {
        	this.addPlosca.add(new JLabel());
        }
        
        //this.addPlosca.add(this.addPlosca.vrniAdd());
        this.addPlosca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.balancePlosca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		/*
		* Starting learning threads -> I will be using two threads:
		* - Main thread
		* - VsebinskaPlosca thread
		* 
		* This thread will be updating the content of JPanel.
		* When the user clicks the button, the state will change 
		* and the update method running within this thread will change the content. 
		*
		*/
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run(){
		running = true;
		while(running) {
			if(state == States.BALANCE) {
				self.removeAll(); //odstranimo prejsnje komponente
                self.setLayout(new GridLayout(1,2));
                self.add(self.balancePlosca);
                self.add(self.seznamTransakcij);
                self.okno.validate();
                self.okno.repaint();
			}else{
                self.removeAll(); //odstranimo prejsnje komponente 
				self.add(self.addPlosca);
				//Po spremembi GUI-ja je potrebno okno validatat in znova repaintat
				//Spremembe so tako uveljavljene
				self.okno.validate();
				self.okno.repaint();
			}
			
			if(Thread.interrupted()) {
				return;
			}
			
			try {
				Thread.sleep(420);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setState(States state) {
		this.state = state;
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
	public JButton vrniBalance() {
		return this.balance;
	}

}