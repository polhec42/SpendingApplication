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
	private JButton transferButton;
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
    private boolean isTransferClicked = false;
    
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
        nastaviGumb(this.incomeButton, color, 2);   
        
        this.transferButton = new JButton("Transfer");
        nastaviGumb(this.transferButton, color, 2);
        
        this.categoryButton = new JButton("Other Category");
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
        
        String[] optionsAccounts = {"Wallet", "Bank", "Drawer"};
        this.accounts = new JComboBox(optionsAccounts);
        this.accounts.setSelectedIndex(0);
        this.accounts.addActionListener(this);
        
        this.amountField = new JTextField("Enter amount: 1.20", 20);
        this.currencyField = new JTextField("Euro", 10);
        this.dateField = new JTextField("Enter date: DD.MM.LLLL", 20);
        
        this.descriptionArea = new JTextArea("Enter description of your transaction");
        
        this.amountField.addFocusListener(this);
        this.currencyField.addFocusListener(this);
        this.dateField.addFocusListener(this);
        this.descriptionArea.addFocusListener(this);
        
        this.categoryButton.addActionListener(this);
        this.expenseButton.addActionListener(this);
        this.incomeButton.addActionListener(this);
        this.transferButton.addActionListener(this);
        this.addButton.addActionListener(this);
	}
    
    public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/2,70)); //nastavimo Å¡irino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
		button.setOpaque(true);
		//button.addActionListener(this);
	}
    
    public void nastaviGumb(JButton button, Color color, int faktor) {
		button.setPreferredSize(new Dimension(this.width/(2*faktor),70)); //nastavimo Å¡irino
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
    public JButton vrniTransferButton() {
    	return this.transferButton;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.categoryButton == e.getSource()) {
			PomoznoOkno pomoznoOkno = new PomoznoOkno("Categories");
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
			izbiraKategorije.add(izbiraKategorije.vrniTable(), BorderLayout.CENTER);
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
		if(e.getSource() == this.expenseButton && !isIncomeClicked && !isTransferClicked) {
			
			isExpenseClicked = !isExpenseClicked;
			
			if(isExpenseClicked) {
				this.expenseButton.setBorder(new LineBorder(Color.RED, 2));
				this.expenseButton.setBorderPainted(true);
			}else {
				this.expenseButton.setBorderPainted(false);
			}
		}
		if(e.getSource() == this.transferButton && !isIncomeClicked && !isExpenseClicked) {
			
			isTransferClicked = !isTransferClicked;
			
			if(isTransferClicked) {
				this.transferButton.setBorder(new LineBorder(Color.RED, 2));
				this.transferButton.setBorderPainted(true);
			}else {
				this.transferButton.setBorderPainted(false);
			}
			this.categories.addItem("Transfer");
			this.categories.setSelectedItem("Transfer");	
		}
		if(e.getSource() == this.incomeButton && !isExpenseClicked && !isTransferClicked) {
			
			isIncomeClicked = !isIncomeClicked;
			
			if(isIncomeClicked) {
				this.incomeButton.setBorder(new LineBorder(Color.RED, 2));
				this.incomeButton.setBorderPainted(true);
			}else {
				this.incomeButton.setBorderPainted(false);
			}
			//Èe je income potem je samo ena možna kategorija: Income
			this.categories.addItem("Income");
			this.categories.setSelectedItem("Income");			
		}
		//Dodane funkcionalnosti vpisa transakcije v podatkovno bazo
		if(e.getSource() == this.addButton && (isExpenseClicked != false || isIncomeClicked != false || isTransferClicked != false)) {
			
			this.addButton.setBorder(null);
			this.dateField.setBorder(null);
			this.amountField.setBorder(null);
			
			if(RightFormat.dateFormat(this.dateField.getText())
					&& RightFormat.amountFormat(this.amountField.getText())){
				
				String description = this.descriptionArea.getText();
				String date = this.dateField.getText();
				String category = (String)this.categories.getSelectedItem();
				double amount = Double.parseDouble(this.amountField.getText());
				String account = (String)this.accounts.getSelectedItem();
				String currency = this.currencyField.getText();
				String type = "";
				if(isExpenseClicked) {
					type = "Expense";
				}else if(isIncomeClicked) {
					type = "Income";
				}else {
					type = "Transfer";
				}
			
				this.test.insert(description, date, account, amount, currency, category, type);
				System.out.printf("%s %s %s %.2f %s %s %s", description, date, category, amount,
						account, currency, type);
				//Posodobim JList pri Balance tako, da imam tudi najnovejšo transakcijo
				//this.seznamTransakcij.vrniDefaultListModel().addElement(description);
				this.addButton.setBorder(new LineBorder(Color.GREEN, 2));
			}else {
				this.addButton.setBorder(new LineBorder(Color.RED, 2));
				
				if(!RightFormat.dateFormat(this.dateField.getText())) {
					this.dateField.setBorder(new LineBorder(Color.RED, 2));
				}
				if(!RightFormat.dateFormat(this.amountField.getText())) {
					this.amountField.setBorder(new LineBorder(Color.RED, 2));
				}
				
			}
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