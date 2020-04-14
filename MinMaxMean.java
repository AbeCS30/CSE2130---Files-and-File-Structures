import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MinMaxMean {
	File file;
	BufferedReader reader;
	int numberOfValues = 0;
	double mean = 0;
	double max = 0;
	double min = 0;
	
	public boolean readFile(String fileName) {
		file = new File(fileName);
		try {
			try {
				reader = new BufferedReader(new FileReader(file));
				String line;
				numberOfValues = 0;
				double fileSum = 0;
				max = Double.MIN_VALUE;
				min = Double.MAX_VALUE;
				while ((line = reader.readLine()) != null) {
					double num = Double.parseDouble(line);
					max = Double.max(max, num);
					min = Double.min(min, num);
					numberOfValues++;
					fileSum += num; 
				}
				reader.close();
				if (numberOfValues > 0) {
					mean = fileSum / (double)numberOfValues;
				} else {
					mean = 0;
					max = 0;
					min = 0;
				}
			} catch (NumberFormatException|NullPointerException er) {
				numberOfValues = 0;
				mean = 0;
				max = 0;
				min = 0;
				reader.close();
				file = null;
				reader = null;
				return false;
			}
			return true;
		} catch(IOException e) {
			numberOfValues = 0;
			mean = 0;
			max = 0;
			min = 0;
			file = null;
			reader = null;
			return false;
		}
	}
	
	public int getNumberOfValues() {
		return numberOfValues;
	}
	
	public double getMean() {
		return mean;
	}
	
	public double getMaximum() {
		return max;
	}
	
	public double getMinimum() {
		return min;
	}
}

