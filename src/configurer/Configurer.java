/*
 * This class configures the algorithms with values specified in executor
 */

package configurer;

import algorithms.Algorithm;
import algorithms.FirstAlgorithm;
import algorithms.SecondAlgorithm;
import algorithms.ThirdAlgorithm;

public class Configurer {

	public void configure(Algorithm algorithm, int z, int p, int r) {
		if(algorithm.getClass() == FirstAlgorithm.class) {
			FirstAlgorithm firstAlgorithm = (FirstAlgorithm) algorithm;
			configureFirstAlgorithm(firstAlgorithm, z, p);
			
		} else if(algorithm.getClass() == SecondAlgorithm.class) {
			SecondAlgorithm secondAlgorithm = (SecondAlgorithm) algorithm;
			configureSecondAlgorithm(secondAlgorithm, p);
			
		} else if(algorithm.getClass() == ThirdAlgorithm.class) {
			ThirdAlgorithm thirdAlgorithm = (ThirdAlgorithm) algorithm;
			configureThirdAlgorithm(thirdAlgorithm, p, r);
		}
	}
	
	private void configureFirstAlgorithm(FirstAlgorithm algorithm, int z, int p) {
		algorithm.setMaxNumberOfDraws(z);
		algorithm.setMaxThreshold(p);
	}
	
	private void configureSecondAlgorithm(SecondAlgorithm algorithm, int p) {
		algorithm.setMaxThreshold(p);
	}
	
	private void configureThirdAlgorithm(ThirdAlgorithm algorithm, int p, int r) {
		algorithm.setMaxThreshold(p);
		algorithm.setMinThreshold(r);
	}
	
}
