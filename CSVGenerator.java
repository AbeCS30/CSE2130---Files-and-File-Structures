import java.io.*;
import java.util.Arrays;
import javax.xml.*;
import javax.xml.parsers.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.*;

public class CSVGenerator {
	PrintWriter writer;
	
	public void open(String fileName, boolean append) {
		File file = new File(fileName);
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add(StudentRecord record) {
		String line = record.getStudentID() + "," + record.getBirthYear() + "," + record.getBirthMonth() + "," + record.getBirthDay() + "," + record.getLastName() + "," + record.getFirstName() + "," + record.getGender();
		writer.print(line + "\r\n");
	}
	
	public void close() {
		writer.close();
	}
	
	public void importFromTXT(String fileName) {
		File file = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(" ");
				for (int i = 0; i < values.length; i++) {
					if (values[i].equals("") || values[i].contentEquals(" ")) {continue;}
					if (i == values.length - 1) {
						writer.write(values[i] + "\r\n");
					} else {
						writer.write(values[i] + ",");
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void importFromCSV(String fileName) {
		File file = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				for (int i = 0; i < values.length; i++) {
					values[i] = values[i].replace("\"", "");
					if (i == values.length - 1) {
						writer.write(values[i] + "\r\n");
					} else {
						writer.write(values[i] + ",");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void importFromXML(String fileName) {
		File file = new File(fileName);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			Document doc = (Document)db.parse(file);
			NodeList nList = doc.getElementsByTagName("Student");
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				Element element = (Element)node;
				String studentID = element.getElementsByTagName("StudentID").item(0).getTextContent();
				String birthYear = element.getElementsByTagName("BirthYear").item(0).getTextContent();
				String birthMonth = element.getElementsByTagName("BirthMonth").item(0).getTextContent();
				String birthDay = element.getElementsByTagName("BirthDay").item(0).getTextContent();
				String lastName = element.getElementsByTagName("LastName").item(0).getTextContent();
				String firstName = element.getElementsByTagName("FirstName").item(0).getTextContent();
				String gender = "";
				try {
					gender = element.getElementsByTagName("Gender").item(0).getTextContent();
				} catch (NullPointerException n) {}
				String line = studentID + ","
							+ birthYear + ","
							+ birthMonth + ","
							+ birthDay + ","
							+ lastName + ","
							+ firstName + ","
							+ gender + "\r\n";
				writer.write(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void importFromJSON(String fileName) {
		JSONParser parser = new JSONParser();
		File file = new File(fileName);
		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader(file));
			for (Object element : array) {
				JSONObject jsonObj = (JSONObject)element;
				long studentID = (long)jsonObj.get("StudentID");
				long birthYear = (long)jsonObj.get("BirthYear");
				long birthMonth = (long)jsonObj.get("BirthMonth");
				long birthDay = (long)jsonObj.get("BirthDay");
				String lastName = (String)jsonObj.get("LastName");
				String firstName = (String)jsonObj.get("FirstName");
				String gender = (String)jsonObj.get("Gender");
				String line = studentID + ","
						+ birthYear + ","
						+ birthMonth + ","
						+ birthDay + ","
						+ lastName + ","
						+ firstName + ","
						+ gender + "\r\n";
				writer.write(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}