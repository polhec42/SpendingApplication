import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Main{
	
	final static int RAZMERJE = 8;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Okno okno = new Okno("Spending app");
		
		MenijskaPlosca stranskiMeni = new MenijskaPlosca(
				okno.getX(), 
				okno.getY(),
				okno.getWidth(), 
				okno.getHeight(),
				Color.DARK_GRAY
		);
		
		VsebinskaPlosca vsebina = new VsebinskaPlosca(
				okno.getX(), 
				okno.getY(),
				okno.getWidth(), 
				okno.getHeight(),
				Color.LIGHT_GRAY
		);
		stranskiMeni.setPreferredSize(new Dimension(
				stranskiMeni.vrniWidth()/RAZMERJE,
				stranskiMeni.vrniHeight()/RAZMERJE
				
		));
		vsebina.setPreferredSize(new Dimension(
				(RAZMERJE-1)*vsebina.vrniWidth()/RAZMERJE,
				(RAZMERJE-1)*vsebina.vrniHeight()/RAZMERJE		
		));
		stranskiMeni.add(stranskiMeni.vrniBalance());
		okno.add(stranskiMeni, BorderLayout.WEST);
		okno.add(vsebina, BorderLayout.EAST);
		
		okno.setVisible(true);
		
		
	}

}
