import java.util.*;

class os {	
	/* INITIALIZATION */
	public static LinkedList<job> jobTable;
    public static int endPointer; //points to the last address for each job
    //public static int current; //keeps track of the current job at job table
	public static int timeSpentCpu; //keeps track of cpu running time
	public static boolean CpuIdling; //flag to know if cpu is in use or not
    public static boolean ioFlag = false;
	public static boolean drumFlag = false;
	public static LinkedList<job> drum = new LinkedList<job>();
	public static LinkedList<job> io = new LinkedList<job>();
	
	
	public static int num = 0;
	public static void startup() {
	  	//sos.ontrace();
		jobTable = new LinkedList<job>(); //init job table
      		endPointer = 0; //start at the beginning
      		//current = 0; //no jobs at the moment start at 0
	  	timeSpentCpu = 0; //init to 0
	  	CpuIdling = false; //at the start cpu is idling
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
		System.out.println("Job Arriving: number " + p[1] + ", priority " +
      		p[2] + ", size " + p[3] + ", cputime " + p[4] + ", currenttime " +p[5]);
		
      		//storing the information of the new jobs into jobTable
	  
	  
	  
	  job newJob = new job(endPointer, p);
	  jobTable.add(newJob);
	  
	  updateTimer(p[5]);
	
	//if(jobTable.get(0).getInDrum() == false) {
	if(drumFlag==false){
			jobTable.get(0).setInDrum(true);
			drumFlag=true;
      		//place job into memory by calling sos (In core)
      		sos.siodrum(p[1], p[3], endPointer, 0);
      		System.out.println("Job now in address, " + endPointer);
			
			jobTable.get(0).setInCore(true);
			
      		//keeping track of the ending of each job in memory
      		endPointer = endPointer + p[3];
      		System.out.println("ending in address, " + endPointer);
		}
	else 
		drum.add(newJob);
	  	//only dealing with 1 job at a time, so cpu is idle
	  	//at the moment
		//must place them into jobTable after
			

	if(jobTable.size() == 1){
		//num++;
	  	CpuIdling = true;
		}
	else
		FirstComeFirstServe(a, p);
	}
   
	//printing on all interrupts to know where the job is at
	
	/* SCHEDULER */
	public static void FirstComeFirstServe(int[] a, int[] p) {
	  	System.out.println("Currently at FCFS ");
	  	System.out.println("CurrentTime   JOB#"+jobTable.get(0).getJobNum()+" = " + jobTable.get(0).getCurrentTime());
	
      		//if there is a job to run, set a to 2
      		a[0] = 2;
      		//put information about job
      		//current job address to update
      		p[2] = jobTable.get(0).getJobAddress();
      		//current job size to p[3]
      		p[3] = jobTable.get(0).getJobSize();
      		//current job time slice to p[4] substract the time it runs to the
	  	//maximum CPU time it requires
      		p[4] = jobTable.get(0).getCpuTime()-jobTable.get(0).getCurrentTime();
	  
	  	//job will be going into the CPU so we set out idling flag to false
	  	CpuIdling = false;
	  	//we use a variable to keep track of the time spent using cpu
	  	timeSpentCpu = p[5];  
	}
   
	/* DISK INTERRUPT */
	public static void Dskint(int[] a, int[] p) {
      		System.out.println("Currently at Dskint "); 
	 
	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	updateTimer(p[5]);
		ioFlag = false;
		
		if(io.size()>=1)
			jobTable.add(io.pop());
		
		
		//go back to schedule the job to run
	  	FirstComeFirstServe(a, p);
	}

	/* DRUM INTERRUPT */
	public static void Drmint(int[] a, int[] p) {
	  	System.out.println("Currently at Drmint "); 
	  
	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	updateTimer(p[5]);
		jobTable.get(0).setInDrum(false);
		
		/*if(drum.size()>=1 && drum.get(0).getJobNum()!=6) {
			jobTable.add(drum.pop());
		}*/
		
		for(int i=0; i<jobTable.size(); i++){
		System.out.println(jobTable.get(i).getJobNum()+"\n");
		}			
		
		drumFlag = false;
	  	//go back to schedule the job to run
      		FirstComeFirstServe(a, p);
	}
	
	/* TIMER-RUN-OUT */
	public static void Tro(int[] a, int[] p) {
	  	System.out.println("Currently at Tro ");
	  
	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	updateTimer(p[5]);
		
		//we check if our time left for the job is less or equal to the time it has been running on the cpu
		if(jobTable.get(0).getCpuTime()<=jobTable.get(0).getCurrentTime()){
			//job is forced out so we increment our current job #
			//set a[0] to 1, to let sos know
			//set the cpu back to idling
		//sos.siodrum(p[1], p[3], endPointer, 1);
		
		System.out.println("JOB# " + jobTable.get(0).getJobNum() + " HAS TERMINATED\n");
				endPointer = endPointer - jobTable.get(0).getJobSize();
				jobTable.remove();
				//current++;
				//num--;
				a[0]=1;
				CpuIdling = true;
				
				if(drum.size()>0)
					jobTable.add(drum.pop());
					
				if(jobTable.size()>0)
					FirstComeFirstServe(a, p);
				//System.out.println(jobTable.size());
			}
		else {
	  	//go back to schedule the job to run
			FirstComeFirstServe(a, p);
		}
	}
	
	/* SUPERVISOR CALL FROM USER PROGRAM */
	public static void Svc(int[] a, int[] p) {
      		System.out.println("Currently at SVC job wants to: " + a[0]);

	  	//if our idling flag is false we want to substract 
	  	//time spent using cpu to the current time and add it
	  	//to the job's running time
	  	//(this is how we keep track of quantum of time)
	  	updateTimer(p[5]);
	  
	  	//job wishes to terminate, so we increment the current job #
	  	//and set the idling flag back to true
	  	if(a[0] == 5) {
		System.out.println("JOB# " + jobTable.get(0).getJobNum() + " HAS TERMINATED\n");
			endPointer = endPointer - jobTable.get(0).getJobSize();
			jobTable.remove();
			//current++;
			//num--;
			a[0] = 1;
			CpuIdling = true;
			
			
			if(drum.size()>0)
					jobTable.add(drum.pop());
		}
	  	//job wants to do io, so we call siodisk
	  	//then go back to scheduler
      		if(a[0] == 6) {
			
				jobTable.get(0).setInIO(true);
				//if(ioFlag==false){				
				sos.siodisk(jobTable.get(0).getJobNum());
				//}
				//if(jobTable.size()>1)
					//io.add(jobTable.pop());
				
				
				
				ioFlag =true;
        		FirstComeFirstServe(a, p);
        	}   
	  	//job wants to block
	 	//since at the moment we know theres no pending io
	  	//we just set idling flag to true
	  	if(a[0] == 7) {
			
			if(ioFlag == false)
			//if(jobTable.get(0).getInIO() == false)
				FirstComeFirstServe(a, p);
			else {
				
				System.out.println("  HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE  ");
				jobTable.get(0).setBlock(true);
				a[0] = 1;
				//if(jobTable.size()>1)
					//sos.siodrum(jobTable.get(0).getJobNum(), jobTable.get(0).getJobSize(), endPointer, 1);
				CpuIdling = true;
				

			}
		}
	}	
	
		public static void updateTimer(int p){
	if(CpuIdling == false) {
			jobTable.get(0).updateCurrentTime(p, timeSpentCpu);
			System.out.println("CurrentTime   JOB#"+ jobTable.get(0).getJobNum() + " = " + jobTable.get(0).getCurrentTime());
		}
	}

}
