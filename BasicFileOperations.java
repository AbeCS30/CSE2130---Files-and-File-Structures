import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class BasicFileOperations {
	private final String CLRF = "\r\n";
	public String readFile(String fileName) {
		File file = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String output = "";
			String line;
			
			try {
				while ((line = reader.readLine()) != null) {
					output += line;
				}
			} catch(IOException e) {
				reader.close();
				return "";
			}
			
			reader.close();
			return output;
		} catch (IOException e) {
		}
		return "";
	}
	public String readFileWithCRLF(String fileName) {
		File file = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String output = "";
			String line;
			
			try {
				while ((line = reader.readLine()) != null) {
					output += line + CLRF;
				}
			} catch(IOException e) {
				reader.close();
				return "";
			}
			
			reader.close();
			return output;
		} catch (IOException e) {}
		return "";
	}
	public void writeFile(String fileName, String[] text) {
		File file = new File(fileName);
		try {
			PrintWriter writer = new PrintWriter(file);
			for (int i = 0; i < text.length; i++) {
				if (i == text.length-1) {
					writer.print(text[i]); //Never throws exception
				} else {
					writer.println(text[i]); //Never throws exception
				}
			}
			writer.close();
		} catch (IOException e) {}
	}
	public void appendFile(String fileName, String line) {
		File file = new File(fileName);
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			writer.println(line); //Never throws exception
			writer.close();
		} catch (IOException e) {}
	}
}
