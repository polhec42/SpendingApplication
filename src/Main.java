import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

public class Main{
	
	final static int RAZMERJE = 16;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Okno okno = new Okno("Spending app");
		Test test = new Test();
		VsebinskaPlosca vsebina = new VsebinskaPlosca(
				okno.getX(), 
				okno.getY(),
				(RAZMERJE-3)*okno.getWidth()/RAZMERJE, 
				(RAZMERJE-3)*okno.getHeight()/RAZMERJE,
				Color.LIGHT_GRAY,
				okno, 
				test
		);
		
		MenijskaPlosca stranskiMeni = new MenijskaPlosca(
				okno.getX(), 
				okno.getY(),
				okno.getWidth(), 
				okno.getHeight(),
				Color.DARK_GRAY, 
				vsebina
		);
		
		stranskiMeni.setPreferredSize(new Dimension(
				stranskiMeni.vrniWidth()/(RAZMERJE/2),
				stranskiMeni.vrniHeight()/(RAZMERJE)
				
		));/*
		vsebina.setPreferredSize(new Dimension(
				(RAZMERJE-2)*vsebina.vrniWidth()/RAZMERJE,
				(RAZMERJE-2)*vsebina.vrniHeight()/RAZMERJE		
		));*/
		
		stranskiMeni.add(stranskiMeni.vrniBalance());
		stranskiMeni.add(stranskiMeni.vrniAdd());
		vsebina.setBorder(new EmptyBorder(10, 15, 10, 10));
		//tko RES nevem zakaj je tuki treba 15
		okno.add(stranskiMeni, BorderLayout.WEST);
		okno.add(vsebina, BorderLayout.CENTER);
		okno.pack();
		okno.setVisible(true);
		
		
		
		
		
	}

}
