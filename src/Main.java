import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Okno okno = new Okno("Spending app");
		
		RisalnaPlosca risalnaPlosca = new RisalnaPlosca(
				okno.getX(), 
				okno.getY(),
				okno.getWidth(), 
				okno.getHeight(),
				Color.DARK_GRAY
		);
		
		RisalnaPlosca risalnaPlosca2 = new RisalnaPlosca(
				okno.getX(), 
				okno.getY(),
				okno.getWidth(), 
				okno.getHeight(),
				Color.LIGHT_GRAY
		);
		//risalnaPlosca.setLayout(new BorderLayout());
		risalnaPlosca.setPreferredSize(new Dimension(
				risalnaPlosca.vrniWidth()/8,
				risalnaPlosca.vrniHeight()/8
				
		));
		risalnaPlosca2.setPreferredSize(new Dimension(
				7*risalnaPlosca2.vrniWidth()/8,
				7*risalnaPlosca2.vrniHeight()/8		
		));
		okno.add(risalnaPlosca, BorderLayout.WEST);
		okno.add(risalnaPlosca2, BorderLayout.EAST);
		//okno.pack();
		okno.setVisible(true);

	}

}
