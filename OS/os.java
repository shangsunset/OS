import java.util.*;

public class os {
	/* INITIALIZATION */
    public static LinkedList<Job> jobTable;
    public static DrumManager drumManager;
    public static CpuScheduler scheduler;
	public static MemoryManager memoryManger;
	public static Job runningJob;
    public static int drumToMemory = 0;
    public static int memoryToDrum = 1;
    public static int time = 0;
	public static ListIterator<Job> i;

    public static void startup() {
	  	//sos.ontrace();
		runningJob = new Job();
		jobTable = new LinkedList<Job>();
        drumManager = new DrumManager(jobTable);
        scheduler = new CpuScheduler();
		memoryManager = new MemoryManager();
		i = jobTable.listIterator();
	}

	/*	
	public static void siodisk(int jobnum);
	public static void siodrum(int jobnum, int jobsize, int coreaddress, int direction);
	*/

	/* INTERRUPT HANDLERS */

	/* INDICATES ARRIVAL OF A NEW JOB ON THE DRUM */
	/* SAVE INFORMATION ABOUT NEW COMING JOB */
	public static void Crint(int[] a, int[] p) {
		//System.out.println("Job Arriving: number " +p[1]+ ", priority "+ p[2]+", size "+p[3]+", cputime "+p[4]+", Arrival time "+p[5]);
        
		bookKeeper(p[5]);
		
		// deletes from the job table and memory, jobs that have finished cpu
		// execution but are blocked waiting to finish io
		while(i.hasNext()) {
			Job j = i.next();
			if(j.isFinished() && /*io queue does not contain (j)*/ ) {
				memoryManager.remove(j.getJobAddress(), j.getJobSize());
				jobTable.setRemove(true);
			}
		}
		while(i.hasNext()) {
			Job j = i.next();
			if(toRemove) {
				jobTable.remove(j);
			}
		}
		
		Job newJob = new Job(memoryMager, p);
		jobTable.add(newJob);
		
		if(newJob.getJobAddress() != -1) {
			drumManager.swapper(newJob, drumToMemory);
			//scheduler.addJobToReadyQueue(newJob);
		}
			
		runningJob = scheduler.roundRobin(a, p, jobTable);
		
        //swap into drum queue
        //drumManager.swapper(runningJob, drumToMemory);

        //put new job to ready queue
        //scheduler.addJobToReadyQueue(runningJob);

        //choose a job to run on cpu
        //scheduler.schedule(a, p);
	}
   
	//printing on all interrupts to know where the job is at
	
	/* SCHEDULER */
	//public static void sched(int[] a, int[] p) { 
	//}
   
	/* DISK INTERRUPT */
	public static void Dskint(int[] a, int[] p) {
		bookKeeper(p[5]);
		
	}

	/* DRUM INTERRUPT */
	public static void Drmint(int[] a, int[] p) {
        bookKeeper(p[5]);

    }
	
	/* TIMER-RUN-OUT */
	public static void Tro(int[] a, int[] p) {
        bookKeeper(p[5]);

        //run out of cpuTime
        if(runningJob.getCpuTimeUsed() == 0) {
                //process finished, indicate CPU is idle now
                scheduler.setCpuIdle(true);
                terminate();
				//Process will leave the CPU after the completion
                //CPU will proceed with the next process in the ready queue
				scheduler.schedule(a, p);
        }
        //run out of a time slice
        else {
                //bookKeeper(p[5]);

                //process finished, indicate CPU is idle now
                scheduler.setCpuIdle(true);

                //Executed process is then placed at the tail of the ready querue by applying the context switch
                scheduler.addJobToRQ(runningJob);


                //CPU scheduler then proceeds by selecting the next process in the ready queue
                scheduler.schedule(a, p);

        }
		
	}
	
	/* SUPERVISOR CALL FROM USER PROGRAM */
	public static void Svc(int[] a, int[] p) {
		bookKeeper(p[5]);
		switch(a[0]) {
			case 5:
				terminate();
				break;
			case 6:
				
				break;
			case 7:
				
				break;
		}
		
		runningJob = schedule.roundRobin(a, p, jobTable);
	}	
	
	public static void terminate() {
		if( /*io queue does not contains the running job*/ ) {
			memoryManager.remove(runningJob.getJobAddress(), runningJob.getJobSize());
			jobTable.remove(runningJob);
		}
		else
			runningJob.setFinished(true);
	}
	
	public static void bookKeeper(int realTime){
        if(!scheduler.isCpuIdle()) {
         	runningJob.updateCpuTimeUsed(realTime, time);
         	//System.out.println("CPU time used = " + runningJob.getCpuTimeUsed());
			time = realTime;
		}
	}

}
