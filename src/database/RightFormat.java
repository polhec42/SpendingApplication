package database;


public class RightFormat {
	
	/*
	 * Pravi format:
	 * xx.xx.xxxx
	 * */
	public static boolean dateFormat(String d) {
		
		String[] components = d.split("");
		
		//Dolžina
		if(components.length != 10){
			return false;
		}
		//Pike
		if(!components[2].equals(".") || !components[5].equals(".")) {
			return false;
		}
		
		try {
			int dan = Integer.parseInt(components[0]+components[1]);
			int mesec = Integer.parseInt(components[3]+components[4]);
			int leto = Integer.parseInt(components[6]+components[7]+components[8]+components[9]);
			
			if(dan > 0 && dan < 32 && mesec > 0 && mesec < 13 && leto > 0) {
				return true;
			}
			
		}catch (NumberFormatException e) {
			System.out.println(e);
			return false;
		}
		
		return false;
	}
	
	public static boolean amountFormat(String s) {
		try {
			Double d = Double.parseDouble(s);
			return true;
		}catch (NumberFormatException e) {
			System.out.println(e);
			return false;
		}
	}
	
}
