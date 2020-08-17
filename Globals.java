import java.awt.Color;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Globals {
	public String cardBackColor;
	public Color bgColor;
	
	public Globals() {
		this.cardBackColor = "blue";
		this.bgColor = new Color(39, 100, 20);
	}
	
	public Globals(String color, Color bgColor) {
		this.cardBackColor = color;
		this.bgColor = bgColor;
	}
	
	public Globals(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			this.cardBackColor = br.readLine();
			this.bgColor = new Color(Integer.parseInt(br.readLine()), 
									 Integer.parseInt(br.readLine()), 
									 Integer.parseInt(br.readLine())
									);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCardBackColor(String color) {
		this.cardBackColor = color;
	}
	
	public void setBGColor(Color c) {
		this.bgColor = c;
	}
	
	public void writeToFile(File f) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			
			bw.write(this.cardBackColor); bw.newLine();
			bw.write(String.valueOf(bgColor.getRed())); bw.newLine();
			bw.write(String.valueOf(bgColor.getGreen())); bw.newLine();
			bw.write(String.valueOf(bgColor.getBlue()));
			
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}