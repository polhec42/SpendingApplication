import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SeznamTransakcij extends JPanel implements ListSelectionListener,ActionListener{
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Test test;
	
	private JList<String> area;
	private JButton izberiButton;
	private JLabel label;
	
	private JScrollPane vertical;
	private DefaultListModel listModel;
	
	private String transactionCategory = "Bank";
	
	public SeznamTransakcij(int x, int y, int width, int height, Test test) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.test = test;
		
		//this.setBounds(this.x, this.y, this.width, this.height);
		
		ArrayList<Transakcija> kategorije = test.vrniTransakcijeIzRacuna(transactionCategory);
		/*
		 * Uporabljam DefaultListModel, saj je tako najlažje posodabljati data za
		 * JList -> to rabim zato, ker želim, da se po dodani transakciji avtomatsko
		 * doda na seznam transakcij, ki je viden pri Balance plošèi
		 * */
		this.listModel=new DefaultListModel();
		
		for(int i = 0; i < kategorije.size(); i++) {
			this.listModel.addElement(kategorije.get(i).getDescription());
		}
		this.area = new JList(this.listModel);
		
		this.area.setPreferredSize(new Dimension(this.width, 60));
		this.vertical = new JScrollPane(area);
		vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//vertical.setBounds(0, 0, this.width, this.height);
		this.izberiButton = new JButton("Izberi");
		nastaviGumb(this.izberiButton, Color.LIGHT_GRAY);
		this.label = new JLabel("Izbira: ");
		area.addListSelectionListener(this);
		izberiButton.addActionListener(this);
	}
	
	public void nastaviGumb(JButton button, Color color) {
		button.setPreferredSize(new Dimension(this.width/4,70)); //nastavimo Å¡irino
		button.setBackground(color); //nastavimo barvo
		button.setBorder(null); //znebimo se obrobe gumba
		button.setFocusable(false); //znebimo se obrobe button icon
	}
	public void nastaviVelikost(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
    }
	/*
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(this.width, this.height);
	}*/
	
	public void posodobiTransakcije() {
		
		ArrayList<Transakcija> kategorije = test.vrniTransakcijeIzRacuna(transactionCategory);
		//Spraznimo ves data in ga znova spišimo
		this.listModel.removeAllElements();
		
		for(int i = 0; i < kategorije.size(); i++) {
			this.listModel.addElement(kategorije.get(i).getDescription());
		}
	}
	
	public DefaultListModel vrniDefaultListModel() {
		return this.listModel;
	}
	
	public JButton vrniIzberiButton() {
		return this.izberiButton;
	}

	public JScrollPane vrniScroll() {
		return this.vertical;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
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
	public JLabel vrniLabel() {
		return this.label;
	}
	public String vrniTransactionCategory() {
		return this.transactionCategory;
	}
	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (this.area.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            this.izberiButton.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            this.izberiButton.setEnabled(true);
	        }
	        label.setText(this.area.getSelectedValue().toString());
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.izberiButton) {
			PomoznoOkno okence = new PomoznoOkno("Podrobnosti");
			PodrobnostiTransakcije podrobnostiTransakcije = new PodrobnostiTransakcije(
					0, 0, okence.getWidth(), okence.getHeight(),
					this.test,
					this.area.getSelectedIndex(),
					transactionCategory
			);
			
			podrobnostiTransakcije.setLayout(new BorderLayout());
			podrobnostiTransakcije.add(podrobnostiTransakcije.getTable(), BorderLayout.CENTER);
		    Border border = new EmptyBorder(10,10,10,10);
		    podrobnostiTransakcije.setBorder(border);
			okence.add(podrobnostiTransakcije);
			okence.pack();
			okence.setVisible(true);
		}
	}
	
}
