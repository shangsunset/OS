import java.util.*;

//RoundRobin
public class CpuScheduler {

    private LinkedList<Job> readyQueue= new LinkedList<Job>();;
    private int timeQuantum = 10;

    public void addJobToReadyQueue(Job newJob) {

        readyQueue.add(newJob);
    }

    public void roundRobin(int[] a, int[] p, LinkedList<Job> jobTable) {

        //as long as there is job in ready queue and CPU is idle, send job to CPU
        while(readyQueue.size() >0 && !readyQueue.get(0).isFinished) {
            //remove the job about to run from ready queue
            jobToRun = readyQueue.remove(0);

            if(readyQueue.size() > 0) {
                //rotate the queue to point to next job. it will be next jobToRun after current jobToRun finishes
                Collections.rotate(readyQueue, 1);
            }

            run(jobToRun, a, p);
        }

    }

    public void run(Job jobToRun, int[] a, int[]p) {

        //send jobToRun to CPU
        a[0] = 2;
        p[2] = jobToRun.getJobAddress();
        p[3] = jobToRun.getJobSize();
        p[4] = timeQuantum;

        //after sending a process to CPU, set CPU to be busy
        isCpuIdle = false;
    }


////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
	/*    FCFS scheduling algorithm     */
	private Job previousJob = new Job(); //will hold the old running job
	public Job FCFS(int[] a, int[] p, LinkedList<Job> jobTable) {
		Job runJob = previousJob;
		if(jobTable.size()==1){
			//table only has 1 job so we set that job to run
			runJob = jobTable.get(0);
			previousJob = runJob;
		}	
		else if(jobTable()>1) {
			//more than 1 job in the table
			for(int i =0; i<jobTable.size(); i++) {
				//check until we find a job to run or for loop ends
				if(jobTable.get(i)==runJob || jobTable.get(i).isBlocked() || jobTable.get(i).isFinished()){
					//jobs that meet any of these conditions wont run on cpu - skip
					continue;
				else
					//as soon as we find a job that can run we exit loop
					runJob = jobTable.get(i);
					break;
				}
			}
		}
		else {
			//no jobs in the table
			a[0] = 1;
			return new Job();
		}
		
		if(runJob.getCpuTimeUsed<=runJob.getMaxCpuTime() && !runJob.isBlocked()) {
			//run job and return the running job to os
			a[0]=2;
			p[2]=runJob.getJobAddress();
			p[3]=runJob.getJobSize();
			p[4]=runJob.getMaxCpuTime - runJob.getCpuTimeUsed();
			return runJob;
		}
		
		//no jobs to run
		a[0] = 1;
		return new Job();
	}
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////

}




















