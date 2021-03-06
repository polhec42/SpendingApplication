package basic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

import database.RightFormat;
import database.Test;
import panels.MenijskaPlosca;
import panels.VsebinskaPlosca;
import windows.Okno;

public class Main{
	
	final static int RAZMERJE = 16;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Init init = new Init();
		Okno okno = new Okno("Spending Application");
		Test test = new Test();
		
		test.setDatabaseName(init.read("database"));

        VsebinskaPlosca vsebina = new VsebinskaPlosca(
				okno.getX(), 
				okno.getY(),
				(RAZMERJE-3)*okno.getWidth()/RAZMERJE, 
				(RAZMERJE-3)*okno.getHeight()/RAZMERJE,
				new Color(Integer.parseInt(init.read("background-color"))),
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
		stranskiMeni.add(stranskiMeni.vrniGraphs());
		stranskiMeni.add(stranskiMeni.vrniSettings());
		vsebina.setBorder(new EmptyBorder(10, 15, 10, 10));
		//tko RES nevem zakaj je tuki treba 15
		okno.add(stranskiMeni, BorderLayout.WEST);
		okno.add(vsebina, BorderLayout.CENTER);
		okno.pack();
		okno.setVisible(true);

		System.out.println(RightFormat.amountFormat("12.0"));

    }

}