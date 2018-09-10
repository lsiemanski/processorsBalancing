package algorithms;

import java.util.List;
import java.util.Random;

import processor.Processor;
import process.Process;

public interface Algorithm {
	public void assignProcess(List<Processor> processors, Process process);
	public int getQueries();
	public int getMigrations();
	
	default boolean checkIfProcessorIsUnderMaxThreshold(Processor processor, int maxThreshold) {
		return processor.getCurrentLoad() < maxThreshold;
	}
	
	default Processor getFirstProcessor(List<Processor> processors, Process process) {
		return processors.get(process.getFirstProcessorId() - 1); 
	}
	
	default Processor drawProcessor(List<Processor> processors, int currentProcessorId) {
		int randomNumber;
		
		do {
			randomNumber = new Random().nextInt(processors.size() - 1);
		} while(randomNumber == currentProcessorId - 1);
		
		return processors.get(randomNumber);
	}
}
