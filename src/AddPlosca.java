import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;

public class AddPlosca extends JPanel implements ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton expenseButton;
	private JButton incomeButton;
    private JButton add;
    private JButton categoryButton;
    
    private JTextField amountField;
    private JTextField currencyField;
    private JTextField dateField;
    
    private JComboBox list;
    
    private Okno okno;
    private Test test;
    
    private boolean isExpenseClicked = false;
    private boolean isIncomeClicked = false;
    
	public AddPlosca(int x, int y, int width, int height, Color color, Okno okno, Test test) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.okno = okno;
		this.test = test;
		
		setBackground(color);
        
        this.expenseButton = new JButton("Expense");
        nastaviGumb(this.expenseButton, color);
        
        this.incomeButton = new JButton("Income");
        nastaviGumb(this.incomeButton, color);    
        
        this.categoryButton = new JButton("Category");
        nastaviGumb(this.categoryButton, color);
        
        /*
         * Drop down menu
         * */
        String[] options = {"Technology", "Gift", "Food"};
        this.list = new JComboBox(options);
        this.list.setSelectedIndex(0);
        this.list.addActionListener(this);
        //
        
        this.amountField = new JTextField(20);
        this.currencyField = new JTextField(10);
        this.dateField = new JTextField(20);
        
        this.categoryButton.addActionListener(this);
        this.expenseButton.addActionListener(this);
        this.incomeButton.addActionListener(this);
        
        this.amountField.addActionListener(this);
        this.currencyField.addActionListener(this);
        this.dateField.addActionListener(this);
	}
    
    public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo Å¡irino
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
    public JButton vrniCategoryButton() {
    	return this.categoryButton;
    }
    public JTextField vrniAmountField() {
    	return this.amountField;
    }
    public JTextField vrniCurrencyField() {
    	return this.currencyField;
    }
    public JTextField vrniDateField() {
    	return this.dateField;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.categoryButton == e.getSource()) {
			PomoznoOkno pomoznoOkno = new PomoznoOkno("Izbira kategorije");
			IzbiraKategorije izbiraKategorije = new IzbiraKategorije(
					0,
					0,
					pomoznoOkno.getWidth(),
					pomoznoOkno.getHeight(),
					this.test,
					this
					);
			
			izbiraKategorije.nastaviVelikost(
					pomoznoOkno.getWidth(),
					pomoznoOkno.getHeight());
			
			izbiraKategorije.setLayout(new BorderLayout());
			izbiraKategorije.add(izbiraKategorije.vrniScroll(), BorderLayout.CENTER);
			izbiraKategorije.add(izbiraKategorije.vrniButton(), BorderLayout.PAGE_END);
			izbiraKategorije.add(izbiraKategorije.vrniTextField(), BorderLayout.PAGE_START);
			
			/*for(int i = 0; i < 3; i++) {
		        	izbiraKategorije.add(new JLabel());
		    }*/
			
		    izbiraKategorije.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    /*
		     * Èe ima neka komponenta že doloèen Border, potem moraš dati nov EmptyBorder
		     * (ala margin/padding), v CompoundBorder.
		     * 
		     * */
		    Border border = izbiraKategorije.getBorder();
		    Border margin = new EmptyBorder(10,10,10,10);
		    izbiraKategorije.setBorder(new CompoundBorder(border, margin));
			pomoznoOkno.add(izbiraKategorije);
			pomoznoOkno.setVisible(true);
		
		}
		if(e.getSource() == this.expenseButton && !isIncomeClicked) {
			
			isExpenseClicked = !isExpenseClicked;
			
			if(isExpenseClicked) {
				this.expenseButton.setBorder(new LineBorder(Color.RED, 2));
				this.expenseButton.setBorderPainted(true);
			}else {
				this.expenseButton.setBorderPainted(false);
			}
		}
		if(e.getSource() == this.incomeButton && !isExpenseClicked) {
			
			isIncomeClicked = !isIncomeClicked;
			
			if(isIncomeClicked) {
				this.incomeButton.setBorder(new LineBorder(Color.RED, 2));
				this.incomeButton.setBorderPainted(true);
			}else {
				this.incomeButton.setBorderPainted(false);
			}
		}
		
	}
    
}