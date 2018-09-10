/*
 * This class executes three algorithms with sent parameters
 */
package executor;

import java.util.ArrayList;
import java.util.List;

import algorithms.Algorithm;
import algorithms.FirstAlgorithm;
import algorithms.SecondAlgorithm;
import algorithms.ThirdAlgorithm;
import configurer.Configurer;
import generator.Generator;
import process.Process;
import processor.Processor;
import reader.Reader;
import report.Report;

public class Executor {
	private int time = 1;
	private List<Processor> processors;
	private List<Process> processes;
	private Report report;
	private Algorithm algorithm;
	
	public void execute(int numberOfProcessors, int intensity, int numberOfProcesses) {
		Generator generator = new Generator();
		
		generator.configure(intensity, 30, 20, 50);
		
		generator.generate(numberOfProcesses);
		
		detailedExecution(numberOfProcessors, intensity, 80, 20, 100);
	}
	
	public void detailedExecution(int numberOfProcessors, int intensity,
						int maxThreshold, int minThreshold, int numberOfDraws) {
		
		System.out.println("\nNEW EXECUTION");
		System.out.println("Number of processors: " + numberOfProcessors);
		System.out.println("Intensity: " + intensity);
		System.out.println("Max processor threshold: " + maxThreshold);
		System.out.println("Min processor threshold: " + minThreshold);
		System.out.println("Number of draws: " + numberOfDraws);
		
		report = new Report();
		
		System.out.println("\nFIRST ALGORITHM");
		
		Algorithm algorithm = new FirstAlgorithm();
		
		Configurer configurer = new Configurer();
		
		configurer.configure(algorithm, numberOfDraws, maxThreshold, minThreshold);
		
		this.executeAlgorithm(numberOfProcessors, algorithm);
		
		System.out.println("\nSECOND ALGORITHM");
		
		algorithm = new SecondAlgorithm();
		
		configurer.configure(algorithm, numberOfDraws, maxThreshold, minThreshold);
		
		this.executeAlgorithm(numberOfProcessors, algorithm);
		
		System.out.println("\nTHIRD ALGORITHM");
		
		algorithm = new ThirdAlgorithm();
		
		configurer.configure(algorithm, numberOfDraws, maxThreshold, minThreshold);
		
		this.executeAlgorithm(numberOfProcessors, algorithm);
	}
	
	public void executeAlgorithm(int n, Algorithm algorithm) {
		this.algorithm = algorithm;
		
		loadProcesses();
		setUpProcessors(n);
		
		do {
			executeOneSecond();
		} while(!(isProcessorsWorkDone() && processes.isEmpty()));
		
		report.printReport();
		time = 1;
	}
	
	private void executeOneSecond() {
		List<Process> arrivingProcesses = findArrivingProcesses();
		int queries = 0;
		int migrations = 0;
		
		for(int i = 0; i < arrivingProcesses.size(); i++) {	
			algorithm.assignProcess(processors, arrivingProcesses.get(i));

			queries += algorithm.getQueries();
			migrations += algorithm.getMigrations();
		}
		
		for(int i = 0; i < processors.size(); i++) {
			processors.get(i).doWork();
		}
		
		report.addToReport(time, processors, queries, migrations);
		
		time++;
	}
	
	private void loadProcesses() {
		Reader reader = new Reader();
		reader.readFile();
		
		processes = reader.getProcesses();
	}
	
	private void setUpProcessors(int n) {
		processors = new ArrayList<Processor>();
		
		for(int i = 1; i <= n; i++) {
			processors.add(new Processor(i));
		}
	}
	
	// this function takes processes arriving at current executor time and removes
	// them from remaining processes list
	private List<Process> findArrivingProcesses() {
		if(processes.isEmpty())
			return new ArrayList<Process>();
		
		Process currentProcess = processes.get(0);
		List<Process> arrivingProcesses = new ArrayList<Process>();
		
		while(currentProcess.getArrivingTime() == time) {
			arrivingProcesses.add(currentProcess);
			processes.remove(currentProcess);
			if(processes.isEmpty())
				break;
			
			currentProcess = processes.get(0);
		}
		
		return arrivingProcesses;
	}
	
	// if work on every processor is done then system work is also done
	private boolean isProcessorsWorkDone() {
		for(int i = 0; i < processors.size(); i++) {
			if(!processors.get(i).isDone()) {
				return false;
			}	
		}
		
		return true;
	}
} 
