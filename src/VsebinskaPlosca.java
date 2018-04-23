import java.awt.*;
import java.awt.Taskbar.State;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class VsebinskaPlosca extends JPanel implements Runnable{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton balance;
	
	States state;
	
	private boolean running;
	
	public VsebinskaPlosca(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setBackground(color);
				
		this.state = States.BALANCE;
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
	
	public void run(){
		running = true;
		while(running) {
			System.out.println("Dela");
			//running = ;
			if(Thread.interrupted()) {
				return;
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
