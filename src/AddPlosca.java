import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;

public class AddPlosca extends JPanel implements ActionListener, FocusListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private JButton expenseButton;
	private JButton incomeButton;
    private JButton categoryButton;
    private JButton addButton;
    
    private JTextField amountField;
    private JTextField currencyField;
    private JTextField dateField;
        
    private JComboBox categories;
    private JComboBox accounts;
    
    private JTextArea descriptionArea;
    
    private Okno okno;
    private Test test;
    private SeznamTransakcij seznamTransakcij;
    
    private boolean isExpenseClicked = false;
    private boolean isIncomeClicked = false;
    
	public AddPlosca(int x, int y, int width, int height, Color color, Okno okno, Test test, SeznamTransakcij seznamTransakcij) {
		this.x = x;
		this.y = y; 
		this.width = width;
		this.height = height;
		this.okno = okno;
		this.test = test;
		this.seznamTransakcij = seznamTransakcij;
		
		setBackground(color);
        
        this.expenseButton = new JButton("Expense");
        nastaviGumb(this.expenseButton, color);
        
        this.incomeButton = new JButton("Income");
        nastaviGumb(this.incomeButton, color);    
        
        this.categoryButton = new JButton("Category");
        nastaviGumb(this.categoryButton, color);
        
        this.addButton = new JButton("Add");
        nastaviGumb(this.addButton, color);
        
        /*
         * Drop down menu
         * */
        String[] optionsCategories = {"Technology", "Gift", "Food"};
        this.categories = new JComboBox(optionsCategories);
        this.categories.setSelectedIndex(0);
        this.categories.addActionListener(this);
        
        String[] optionsAccounts = {"Wallet", "Crypto", "Bank"};
        this.accounts = new JComboBox(optionsAccounts);
        this.accounts.setSelectedIndex(0);
        this.accounts.addActionListener(this);
        
        this.amountField = new JTextField("Enter amount", 20);
        this.currencyField = new JTextField("Enter currency", 10);
        this.dateField = new JTextField("Enter date: DD.MM.LLLL", 20);
        
        this.descriptionArea = new JTextArea("Enter description of your transaction");
        
        this.amountField.addFocusListener(this);
        this.currencyField.addFocusListener(this);
        this.dateField.addFocusListener(this);
        this.descriptionArea.addFocusListener(this);
        
        this.categoryButton.addActionListener(this);
        this.expenseButton.addActionListener(this);
        this.incomeButton.addActionListener(this);
        this.addButton.addActionListener(this);
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
    public JComboBox vrniCategoriesList(){
        return this.categories;
    }
    public JButton vrniAddButton(){
        return this.addButton;
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
    public JComboBox vrniAccountsList() {
    	return this.accounts;
    }
    public JTextArea vrniDescriptionArea() {
    	return this.descriptionArea;
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
		//Dodane funkcionalnosti vpisa transakcije v podatkovno bazo
		if(e.getSource() == this.addButton && (isExpenseClicked != false || isIncomeClicked != false)) {
			String description = this.descriptionArea.getText();
			String date = this.dateField.getText();
			String category = (String)this.categories.getSelectedItem();
			double amount = Double.parseDouble(this.amountField.getText());
			String account = (String)this.accounts.getSelectedItem();
			String currency = this.currencyField.getText();
			String type = (isExpenseClicked) ? "Expense" : "Income";
			
			this.test.insert(description, date, account, amount, currency, category, type);
			System.out.printf("%s %s %s %.2f %s %s %s", description, date, category, amount,
					account, currency, type);
			//Posodobim JList pri Balance tako, da imam tudi najnovejšo transakcijo
			this.seznamTransakcij.vrniDefaultListModel().addElement(description);
		}
	}
	//When focus is gained, we delete placeholder
	public void focusGained(FocusEvent e) {
		if(e.getSource() == this.amountField) {
			this.amountField.setText("");
		}
		if(e.getSource() == this.currencyField) {
			this.currencyField.setText("");
		} 
		if(e.getSource() == this.dateField) {
			this.dateField.setText("");
		}
		if(e.getSource() == this.descriptionArea) {
			this.descriptionArea.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		
	}
    
}