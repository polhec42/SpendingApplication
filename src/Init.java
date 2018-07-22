import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Init {
	
	private File file;
	
	public Init(){
		
		this.file = new File("resources/config.txt");
		
	}
	
	public String read(String atribute) {
		try {
			String line;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
			while((line = bufferedReader.readLine()) != null && !line.contains(atribute)) {

			}
			bufferedReader.close();
			String[] array = line.split("=");
			String value = array[1];
			return value;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void modify(String atribute, String value) {
		
		try {
			ArrayList<String> lines = new ArrayList();
			String line;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
			while((line = bufferedReader.readLine()) != null) {
				if(line.contains(atribute)) {
					line = atribute+"="+value;
				}
				lines.add(line);
			}
			bufferedReader.close();

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file, false));
			for(String l : lines) {
				bufferedWriter.write(l);
				bufferedWriter.write("\n");
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write(String atribute, String value) {
		String string = String.format("%s=%s", atribute, value);
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file, true));
			bufferedWriter.write(string);
			bufferedWriter.write("\n");
			bufferedWriter.flush();
			bufferedWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	

}
