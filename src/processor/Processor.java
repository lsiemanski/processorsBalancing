/*
 * Representation of single processor 
 */

package processor;

import java.util.ArrayList;
import java.util.Random;

import algorithms.Algorithm;
import process.Process;

public class Processor {
	
	private int id;
	private ArrayList<Process> processList = new ArrayList<Process>();
	private int currentLoad = 0;
	
	public Processor(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getCurrentLoad() {
		return currentLoad;
	}
	
	public void addProcess(Process newProcess) {
		processList.add(newProcess);
	}
	
	public Process removeRandomProcess() {
		if(processList.size() > 1) {
			int randomNumber = new Random().nextInt(processList.size() - 1);
			return processList.remove(randomNumber);
		}
		
		return processList.remove(0);
	}
	
	public int takeProcessesFromProcessor(Processor processor, int maxThresholdTaken) {
		int currentThresholdTaken = 0;
		Process takenProcess;
		int processesMigrated = 0;
		
		while(currentThresholdTaken < maxThresholdTaken) {
			if(processor.isDone())
				break;
			
			// remove process from selected processor
			takenProcess = processor.removeRandomProcess();
			processor.updateLoad();
			
			// add process to current processor
			this.addProcess(takenProcess);
			this.updateLoad();
			
			currentThresholdTaken += takenProcess.getNeededPower();
			processesMigrated++;
		}
		
		return processesMigrated; // function returns number of migrations
								  // that value will be used for report purposes
	}
	
	public void doWork() {
		Process currentProcess;
		for(int i = 0; i < processList.size(); i++) {
			currentProcess = processList.get(i);
			currentProcess.execute();
			
			// done processes will be removed from process list
			if(currentProcess.getExecutionTime() <= 0) {
				processList.remove(currentProcess);
			}
		}
		
		updateLoad();
	}
	
	private void updateLoad() {
		int load = 0;
		
		for(int i = 0; i < processList.size(); i++) {
			load += processList.get(i).getNeededPower();
		}
		
		currentLoad = load;
	}

	public boolean isDone() {
		return processList.isEmpty();
	}
}
