package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import process.Process;

public class Reader {
	private static final String FILENAME = "processes.txt";
	private List<Process> processes = new ArrayList<Process>();
	
	public Reader() {}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
			String line;
			String[] splitted;
			while((line = reader.readLine()) != null) {
				splitted = line.split(", ");
				processes.add(new Process(toInt(splitted[0]),
										toInt(splitted[1]),
										toInt(splitted[2]),
										toInt(splitted[3]),
										toInt(splitted[4])));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private int toInt(String s) {
		return Integer.parseInt(s);
	}
	
	public List<Process> getProcesses() {
		return processes;
	}
}
