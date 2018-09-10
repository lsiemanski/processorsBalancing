/*
 * This class generates reports that will be written in console after each execution of algorithm
 */

package report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processor.Processor;

public class Report {
	private HashMap<Integer, Double> averageLoad = new HashMap<Integer, Double>(); // time and load pairs
	private HashMap<Integer, Double> averageDeviationOfLoad = new HashMap<Integer, Double>();
	private HashMap<Integer, Integer> numberOfQueries = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> numberOfMigrations = new HashMap<Integer, Integer>();
	
	public Report() {}
	
	public List<Integer> getLoads(List<Processor> processors) {
		List<Integer> loads = new ArrayList<Integer>();
		
		for(Processor processor : processors) {
			loads.add(processor.getCurrentLoad());
		}
		
		return loads;
	}
	
	public double countAverageLoad(List<Integer> loads) {
		return loads.stream().mapToDouble(a -> a).average().getAsDouble();
	}
	
	public double countAverageDeviationOfLoad(List<Integer> loads, double average) {
		int squareSum = 0;
		
		for(int load : loads) {
			squareSum += Math.pow(load - average, 2);
		}
		
		return Math.sqrt((squareSum) / (loads.size() - 1));
	}
	
	public void addToReport(int time, List<Processor> processors, int queries, int migrations) {
		List<Integer> loads = getLoads(processors);
		double average = countAverageLoad(loads);
		
		averageLoad.put(time, average);
		averageDeviationOfLoad.put(time, countAverageDeviationOfLoad(loads, average));
		numberOfQueries.put(time, queries);
		numberOfMigrations.put(time, migrations);
	}

	public void printReport() {
		System.out.println(averageLoad);
		System.out.println(averageDeviationOfLoad);
		System.out.println(numberOfQueries);
		System.out.println(numberOfMigrations);
		
		printTotalReport();
	}

	private void printTotalReport() {
		System.out.println("AVERAGE LOAD: " + getAverageOfLoads());
		System.out.println("AVERAGE DEVIATION: " + getAverageOfDeviation());
		System.out.println("TOTAL QUERIES: " + getTotalQueries());
		System.out.println("TOTAL MIGRATIONS: " + getTotalMigrations());
	}

	private double getAverageOfLoads() {
		return averageOfMapValues(averageLoad);
	}

	private double getAverageOfDeviation() {
		return averageOfMapValues(averageDeviationOfLoad);
	}
	
	// helper function to count average from average loads and standard deviation values 
	private double averageOfMapValues(HashMap<Integer, Double> map) {
		double sum = 0;
		
		for(Map.Entry<Integer, Double> entry : map.entrySet()) {
			sum += entry.getValue();
		}
		
		return sum/map.size();
	}
	
	private int getTotalQueries() {
		return totalOfMapValues(numberOfQueries);
	}
	
	private int getTotalMigrations() {
		return totalOfMapValues(numberOfMigrations);
	}

	// helper function to count total from queries and migrations maps
	private int totalOfMapValues(HashMap<Integer, Integer> map) {
		int total = 0;
		
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			total += entry.getValue();
		}
		
		return total;
	}
}
