package main;

import algorithms.Algorithm;
import algorithms.FirstAlgorithm;
import algorithms.SecondAlgorithm;
import algorithms.ThirdAlgorithm;
import configurer.Configurer;
import executor.Executor;
import generator.Generator;

public class MainClass {

	public static void main(String[] args) {
		Executor executor = new Executor();
		
		executor.execute(50, 20, 10000);
		executor.execute(50, 35, 10000);
		executor.execute(50, 50, 10000);
		
		executor.execute(75, 20, 10000);
		executor.execute(75, 35, 10000);
		executor.execute(75, 50, 10000);
		
		executor.execute(100, 20, 10000);
		executor.execute(100, 35, 10000);
		executor.execute(100, 50, 10000);
	}

}
