/*
 * Representation of system process 
 */

package process;

public class Process {
	private int id;
	private int arrivingTime;
	private int executionTime;
	private int neededPower;
	private int firstProcessorId;
	
	public Process(int id, int arrivingTime, int executionTime, int neededPower, int firstProcessorId) {
		this.id = id;
		this.arrivingTime = arrivingTime;
		this.executionTime = executionTime;
		this.neededPower = neededPower;
		this.firstProcessorId = firstProcessorId;
	}
	
	public Process(int executionTime, int neededPower, int firstProcessorId) {
		this.executionTime = executionTime;
		this.neededPower = neededPower;
		this.firstProcessorId = firstProcessorId;
	}

	public Process() {}

	public void execute() {
		this.executionTime -= 1;
	}
	
	public int getId() {
		return id;
	}

	public int getArrivingTime() {
		return arrivingTime;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public int getNeededPower() {
		return neededPower;
	}

	public int getFirstProcessorId() {
		return firstProcessorId;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setArrivingTime(int arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}

	public void setNeededPower(int neededPower) {
		this.neededPower = neededPower;
	}

	public void setFirstProcessorId(int firstProcessorId) {
		this.firstProcessorId = firstProcessorId;
	}
}
