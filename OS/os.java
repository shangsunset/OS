import java.util.*;

class os {	
	/* INITIALIZATION */
	public static LinkedList<job> jobTable;
    public static int starCoreAddr; //points to the last address for each job
    public static int current; //keeps track of the current job at job table
	public static int cpuTimeUsed; //keeps track of cpu running time
	public static boolean isCpuIdle; //flag to know if cpu is in use or not
	public static boolean ioFlag;
   
	public static void startup() {
	  	sos.ontrace();
		jobTable = new LinkedList<job>(); //init job table
        starCoreAddr = 0; //start at the beginning
        current = 0; //no jobs at the moment start at 0
	  	cpuTimeUsed = 0; //init to 0
	  	isCpuIdle = false; //at the start cpu is idling
		ioFlag = false;
	}

	/*	
	public static void siodisk(int jobnum);
	public static void siodrum(int jobnum, int jobsize, int coreaddress, int direction);
	*/

	/* INTERRUPT HANDLERS */

	/* INDICATES ARRIVAL OF A NEW JOB ON THE DRUM */
	/* SAVE INFORMATION ABOUT NEW COMING JOB */
	public static void Crint(int[] a, int[] p) {
      		//printing everything for debugging purposes
		System.out.println("|Job Arriving: number " + p[1] + ", priority " +
      		p[2] + ", size " + p[3] + ", cpu time " + p[4] + ", current time " +p[5]);
		
      		//storing the information of the new jobs into jobTable
	  	job newJob = new job(starCoreAddr, p);
	  	jobTable.add(newJob);
	  
		updateTimer(p[5]);
	  
      		//place job into memory by calling sos (In core)
      		sos.siodrum(jobTable.get(current).getJobNum(), jobTable.get(current).getJobSize(), starCoreAddr, 0);
      		System.out.println("|Job now in address, " + starCoreAddr);
      
      		//keeping track of the ending of each job in memory
      		starCoreAddr = starCoreAddr + jobTable.get(current).getJobSize();
      		System.out.println("|ending in address, " + starCoreAddr);

	  	//only dealing with 1 job at a time, so cpu is idle
	  	//at the moment
	  	isCpuIdle = true;
	}
   
	//printing on all interrupts to know where the job is at
	
	/* SCHEDULER */
	public static void FirstComeFirstServe(int[] a, int[] p) {
	  	System.out.println("|Currently at FCFS ");
	  	System.out.println("|CurrentTime = " + jobTable.get(current).getCurrentTime());
	
      		//if there is a job to run, set a to 2
      		a[0] = 2;
      		//put information about job
      		//current job address to update
      		p[2] = jobTable.get(current).getJobAddress();
      		//current job size to p[3]
      		p[3] = jobTable.get(current).getJobSize();
      		//current job time slice to p[4] substract the time it runs to the
	  	//maximum CPU time it requires
      		p[4] = jobTable.get(current).getmaxCpuTime()-jobTable.get(current).getCurrentTime();
	  
	  	//job will be going into the CPU so we set out idling flag to false
	  	isCpuIdle = false;
	  	//we use a variable to keep track of the time spent using cpu
	  	cpuTimeUsed = p[5];  
	}

   
	/* DISK INTERRUPT */
	public static void Dskint(int[] a, int[] p) {
      		System.out.println("|Currently at Dskint "); 
	 
	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	/*
		if(isCpuIdle == false) {
			jobTable.get(current).updateCurrentTime(p[5], cpuTimeUsed);
			System.out.println("CurrentTime = " + jobTable.get(current).getCurrentTime());
		}
		*/
		ioFlag = false; //finished doing io
		updateTimer(p[5]);
	
	  	//go back to schedule the job to run
	  	FirstComeFirstServe(a, p);
	}

	/* DRUM INTERRUPT */
	public static void Drmint(int[] a, int[] p) {
	  	System.out.println("|Currently at Drmint "); 
	  
	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	/*
		if(isCpuIdle == false) {
			jobTable.get(current).updateCurrentTime(p[5], cpuTimeUsed);
			System.out.println("CurrentTime = " + jobTable.get(current).getCurrentTime());
		}
		*/
		
		updateTimer(p[5]);
	  	//go back to schedule the job to run
      		FirstComeFirstServe(a, p);
	}
	
	/* TIMER-RUN-OUT */
	public static void Tro(int[] a, int[] p) {
	  	System.out.println("|Currently at Tro ");
	  
	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	/*
		if(isCpuIdle == false) {
			jobTable.get(current).updateCurrentTime(p[5], cpuTimeUsed);
			System.out.println("CurrentTime = " + jobTable.get(current).getCurrentTime());
		}
		*/
		updateTimer(p[5]);
		
		if(jobTable.get(current).getmaxCpuTime() <= jobTable.get(current).getCurrentTime()) {
			//job is forced out so we increment our current job #
			//set a[0] to 1, to let sos know
			//set the cpu back to idling 
				current++;
				a[0]=1;
				isCpuIdle = true;
			}
		else {
		//go back to schedule a job to run
			FirstComeFirstServe(a, p);
		}
		
	  	//go back to schedule the job to run
	  	FirstComeFirstServe(a, p);
	}
	
	/* SUPERVISOR CALL FROM USER PROGRAM */
	public static void Svc(int[] a, int[] p) {
      		System.out.println("|Currently at SVC job wants to: " + a[0]);

	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	/*
		if(isCpuIdle == false) {
			jobTable.get(current).updateCurrentTime(p[5], cpuTimeUsed);
			System.out.println("CurrentTime = " + jobTable.get(current).getCurrentTime());
		}
		*/
		updateTimer(p[5]);



        switch (a[0]) {
            //job wishes to terminate, so we increment the current job #
            //and set the idling flag back to true
            case 5:
                current++;
                a[0] = 1;
                isCpuIdle = true;
                System.out.println("|JOB# " + current + " HAS TERMINATED\n");
                break;
            //job wants to do io, so we call siodisk
            //then go back to scheduler
            case 6:

                ioFlag = true; //is doing io
                sos.siodisk(jobTable.get(current).getJobNum());
                FirstComeFirstServe(a, p);
                break;
            //job wants to be blocked
            //since at the moment we know theres no pending io
            //we just set idling flag to true
            case 7:
                if(!ioFlag)
                    FirstComeFirstServe(a, p); //schedule a job to run
                else { //blocks
                    a[0] = 1;
                    isCpuIdle = true;
                }
                break;
        }
	}	
	
	public static void updateTimer(int p) {
		if(!isCpuIdle) {
			jobTable.get(current).updateCurrentTime(p, cpuTimeUsed);
			System.out.println("|CurrentTime = " + jobTable.get(current).getCurrentTime());
		}
	}

}

