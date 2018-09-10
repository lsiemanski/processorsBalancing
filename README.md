## Processors loads balancing algorithms implementation

### Simulation of three algorithms which balance loads of processors in distributed systems. Created as an exercise for operating systems class.

## Exercise assumptions
- We've got N of identical processors in distributed system (specified in each simulation).
- New processes appear on each processor with different frequency and different needs of power.
- As new process appears program decides, if it should stay on current processor or send it to different one. The manner of choice is determined by algorithm that is currently used.

## First algorithm
- Processor on which process arrived sends a query to randomly selected processor about its current load.
- If the load is lower than specified max threshold, process migrates to drawn processor.
- If the load is higher, algorithm draws different processor. Algorithm has got specified number of maximum draws. After reaching that number drawing will stop and process will be run on current processor.

## Second algorithm
If processor on which process arrived is under max threshold then process will be executed on it. If not, we take same approach as in first algorithm. The only difference is no number of maximum draws.

## Third algorithm
Same approach as in second algorithm but this time we've got additional action on processor. At the end of every clock tick, the processors which are under the specified minimum threshold value will take random processes from processors which are over the specified maximum threshold value. 

## Results
After each simulation, console prints the report which contains:
- Average load of processors in system
- Average standard deviation of loads
- Total number of queries 
- Total number of migrations

## Each simulation is run with specified:
- Number of processors - 50, 75 or 100
- Maximum number of processes arriving at one clock tick - 20, 35 or 50
- Number of total processes in simulation - 10000

## Other values in simulation that are constant:
- Maximum time of process execution - 30
- Maximum power needed by process - 20
- Number of processors on which processes arrive - 50
- Maximum power threshold - 80
- Minimum power threshold - 20
- Maximum number of draws - 100 
