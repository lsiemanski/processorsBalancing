/*
 * This class generates processes which will be later executed in simulation.
 */
package generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import process.Process;

public class Generator {
	private static final String FILENAME = "processes.txt";
	private List<Process> processes = new ArrayList<Process>();
	
	private int arrivingProcessesRange = 20;
	private int executionTimeRange = 20;
	private int neededPowerRange = 20;
	private int processorsRange = 50;
	
	public void generate(int count) {
		Process newProcess;
		int j = 0;
		int time = 0;
		
		for(int i = 1; i <= count; i++) {
			newProcess = new Process();
			newProcess.setId(i);
			
			if(j <= 0) { // we generated all processes that will arrive at current time value
				time++;
				j = drawNumberOfProcessesArrivingAtTime(arrivingProcessesRange);
			}
			
			newProcess.setArrivingTime(time);
			j--;
			
			newProcess.setExecutionTime(new Random().nextInt(executionTimeRange));
			newProcess.setNeededPower(new Random().nextInt(neededPowerRange));
			newProcess.setFirstProcessorId(new Random().nextInt(processorsRange - 1) + 1);
			
			processes.add(newProcess);
		}
		
		writeProcesses();
	}
	
	public void configure(int arrivingProcessesRange, int executionTimeRange,
						  int neededPowerRange, int processorsRange) {
		this.arrivingProcessesRange = arrivingProcessesRange;
		this.executionTimeRange = executionTimeRange;
		this.neededPowerRange = neededPowerRange;
		this.processorsRange = processorsRange;
	}
	
	// write generated processes to file
	private void writeProcesses() {
		StringBuilder sb;
		Process currentProcess;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
			
			for(int i = 0; i < processes.size(); i++) {
				sb = new StringBuilder("");
				currentProcess = processes.get(i);
				sb.append(currentProcess.getId() + ", ");
				sb.append(currentProcess.getArrivingTime() + ", ");
				sb.append(currentProcess.getExecutionTime() + ", ");
				sb.append(currentProcess.getNeededPower() + ", ");
				sb.append(currentProcess.getFirstProcessorId());
				if(i < processes.size() - 1) {
					sb.append("\n");
				}
				writer.write(sb.toString());
				writer.flush();
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	private int drawNumberOfProcessesArrivingAtTime(int range) {
		return new Random().nextInt(range);
	}
}
