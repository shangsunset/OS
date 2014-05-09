import java.util.*;

public class CpuScheduler {
	/*    Round Robin scheduling algorithm     */
	private int timeQuantum = 20;
	private Job previousJob = new Job(); //will hold the old running job
	public Job roundRobin(int[] a, int[] p, LinkedList<Job> jobTable) {
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
		
		if(!(runJob.getMaxCpuTime()==runJob.getCpuTimeUsed())) {
			if(runJob.getMaxCpuTime() < timeQuantum + runJob.getCurrentTime() && !runJob.isBlocked() && runJob.isInMemory()) {
				//run job and return the running job to os
				a[0]=2;
				p[2]=runJob.getJobAddress();
				p[3]=runJob.getJobSize();
				p[4]=runJob.getMaxCpuTime - runJob.getCpuTimeUsed();
				return runJob;
			}
			else if(runJob.getMaxCpuTime() >= timeQuantum && !runJob.isBlocked() && runJob.isInMemory()) {
				//run job and return the running job to os
				a[0]=2;
				p[2]=runJob.getJobAddress();
				p[3]=runJob.getJobSize();
				p[4]=timeQuantum;
				return runJob;
			}
		}
		
		//no jobs to run
		a[0] = 1;
		return new Job();
	}
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////

}




















