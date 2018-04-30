import java.awt.*;
import javax.swing.*;

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
	
	private Okno okno;
	
	public VsebinskaPlosca(int x, int y, int width, int height, Color color, Okno okno) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.okno = okno;
		setBackground(color);
				
		this.state = States.BALANCE;
		/*
		 * Dodajanje BalancePlosca na vsebinsko plosco.
		 * 
		 * 
		 */
		this.balancePlosca = new BalancePlosca(this.x, this.y, this.width, this.height, Color.BLACK);
		this.balancePlosca.add(this.balancePlosca.vrniButton());
		
        this.addPlosca = new AddPlosca(this.x, this.y, this.width, this.height, color);
        this.addPlosca.nastaviVelikost(this.width, this.height);
        this.addPlosca.setLayout(new GridLayout(6, 2));
        this.addPlosca.add(this.addPlosca.vrniExpenseButton());
        this.addPlosca.add(this.addPlosca.vrniIncomeButton());
        this.addPlosca.add(this.addPlosca.vrniList());
        //this.addPlosca.add(this.addPlosca.vrniAdd());
        this.addPlosca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
				System.out.println("Balance");
                self.removeAll(); //odstranimo prejšnje komponente
                self.add(self.balancePlosca);
                self.okno.validate();
                self.okno.repaint();
			}else{
				System.out.println("Add");
                self.removeAll(); //odstranimo prejšnje komponente 
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
