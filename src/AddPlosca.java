import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class AddPlosca extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton expenseButton;
	private JButton incomeButton;
    private JButton add;
    
    private JComboBox list;
    
	public AddPlosca(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		
		setBackground(color);
        
        this.expenseButton = new JButton("Expense");
        nastaviGumb(this.expenseButton, color);
        
        this.incomeButton = new JButton("Income");
        nastaviGumb(this.incomeButton, color);    
        
        
        String[] options = {"1", "2", "3", "4", "5"};
        this.list = new JComboBox(options);
        this.list.setSelectedIndex(0);
        this.list.addActionListener(this);
        //this.add = new JButton("Add");
        //nastaviGumb(this.add, color);
        

        
	}
    
    public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo širino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
		//button.addActionListener(this);
	}
    
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
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
	public JButton vrniExpenseButton() {
		return this.expenseButton;
	}
    public JButton vrniIncomeButton(){
        return this.incomeButton;
    }
    public JComboBox vrniList(){
        return this.list;
    }
    public JButton vrniAdd(){
        return this.add;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
    
}