import java.util.*;
public class os {
    /* INITIALIZATION */
    public static LinkedList<Job> jobTable;
    public static DrumManager drumManager;
    public static CpuScheduler scheduler;
    public static MemoryManager memoryManager;
    public static IoManager ioManager;
    public static Job runningJob;
    public static int time;
    public static ListIterator<Job> itr;
	
    	public static void startup() {
	  	//sos.ontrace();
		jobTable = new LinkedList<Job>();
    	drumManager = new DrumManager();
    	scheduler = new CpuScheduler();
		memoryManager = new MemoryManager();
		ioManager = new IoManager();
		runningJob = new Job();
		time = 0;
    	}

	public static void Crint(int[] a, int[] p) {        
		bookKeeper(p[5]);
		
		// deletes from the job table and memory, jobs that have finished cpu
		// execept jobs that are doing io
		
		itr = jobTable.listIterator();
		LinkedList<Job> toRemove = new LinkedList<Job>();
		while(itr.hasNext() == true) {
			Job j = itr.next();
			if(j.isFinished() && !j.isInIO()) {
				memoryManager.removeJob(j.getJobAddress(), j.getJobSize());
				toRemove.add(j);
			}
		}
		itr = toRemove.listIterator();
		while(itr.hasNext() == true) {
			Job j = itr.next();
			jobTable.remove(j);
		} 
		
		
		Job newJob = new Job(memoryManager, p);
		
		jobTable.add(newJob);
		
		newJob.findSpace();
		
		if(newJob.getJobAddress() != -1)
			drumManager.queue(newJob, 0);
		
		drumManager.swapper(jobTable);

		runningJob = scheduler.roundRobin(a, p, jobTable);
	}
	
	/* DISK INTERRUPT */
	public static void Dskint(int[] a, int[] p) {
		bookKeeper(p[5]);
		
		ioManager.removeIO();
		
		drumManager.swapper(jobTable);
		
		runningJob = scheduler.roundRobin(a, p, jobTable);
	}

	/* DRUM INTERRUPT */
	public static void Drmint(int[] a, int[] p) {
        bookKeeper(p[5]);
		
		drumManager.doneSwapping();
		
		drumManager.swapper(jobTable);
		
		runningJob = scheduler.roundRobin(a, p, jobTable);
    	}
	
	/* TIMER-RUN-OUT */
	public static void Tro(int[] a, int[] p) {
        bookKeeper(p[5]);

        //run out of cpuTime
        if(runningJob.getMaxCpuTime() - runningJob.getCpuTimeUsed() == 0)
            terminate();
        
        drumManager.swapper(jobTable);
		
		runningJob = scheduler.roundRobin(a, p, jobTable);
	}
	
	/* SUPERVISOR CALL FROM USER PROGRAM */
	public static void Svc(int[] a, int[] p) {
		bookKeeper(p[5]);
		switch(a[0]) {
			case 5:
				terminate();
				break;
			case 6:
				ioManager.addToIO(runningJob);
				break;
			case 7:
				if(runningJob.isInIO())
					runningJob.setBlocked(true);
				break;
		}
		
		drumManager.swapper(jobTable);
		
		runningJob = scheduler.roundRobin(a, p, jobTable);
	}	
	
	public static void terminate() {
		if(!runningJob.isInIO()) {
			memoryManager.removeJob(runningJob.getJobAddress(), runningJob.getJobSize());
			jobTable.remove(runningJob);
		}
		else
			runningJob.setFinished(true);
	}
	
	public static void bookKeeper(int currentTime){
		if(runningJob.getJobNumber()!=-1) {
			runningJob.updateCpuTimeUsed(currentTime, time);
			time = currentTime;
		}	
	}

}
