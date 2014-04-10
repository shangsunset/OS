import java.util.*;

class os {
	
	/* INITIALIZATION */
	public static LinkedList<job> jobTable;
   public static int endPointer; //points to the last address for each job
                                 //or 0 for 1st job in
   public static int current; //keeps track of the current job at job table

	public static void startup() {
	   jobTable = new LinkedList<job>(); //Initialize job table
      endPointer = 0;  //Start at the beginning
      current = 0; //no jobs at the moment start at 0
	}

	/*	
	public static void siodisk(int jobnum);

	public static void siodrum(int jobnum, int jobsize, int coreaddress, int direction);

	void ontrace();

	void offtrace();
	*/

	/* INTERRUPT HANDLERS */

	/* INDICATES ARRIVAL OF A NEW JOB ON THE DRUM */
	/* SAVE INFORMATION ABOUT NEW COMING JOB */
	public static void Crint(int[] a, int[] p) {
   
   //At the moment the crint only accepts a job and
   //tries to run it immediately
   //no checking wether its full, busy, etc
   //Just trying to get 1 job running first
   
      //printing everything for debugging purposes
		System.out.println("Job Arriving: number " +p[1]+ ", priority "+
      p[2]+", size "+p[3]+", cputime "+p[4]+", currenttime "+p[5]);
		
      //storing theinformation of the new jobs into jobTable
		job newJob = new job(endPointer, p);
		jobTable.add(newJob);
      
      //place job into memory by calling sos (In core)
      sos.siodrum(p[1], p[3], endPointer, 0);
      System.out.println("Job now in address, " + endPointer);
      
      //keeping track of the ending of each job in memory
      endPointer = endPointer+p[3];
      System.out.println("ending in address, "+ endPointer);
      
      FirstComeFirstServe(a, p);
      
      
      
      
	}
   
   // I put a print on all interrupts to know where the job is at
   
   public static void FirstComeFirstServe(int[] a, int[] p) {
     System.out.println("Currently at FCFS "); 
      //if there is a job to run, set a to 2
      a[0] = 2;
      //put information about job
      //current job address to p[2]
      p[2] = jobTable.get(current).getJobAddress();
      //current job size to p[3]
      p[3] = jobTable.get(current).getJobSize();
      //current job time slice to p[4]
      p[4] = jobTable.get(current).getCpuTime();
   }
      
   
	/* DISK INTERRUPT */
	public static void Dskint(int[] a, int[] p) {
     System.out.println("Currently at Dskint "); 
	}

	/* DRUM INTERRUPT */
	public static void Drmint(int[] a, int[] p) {
	  System.out.println("Currently at Drmint "); 
	}
	
	/* TIMER-RUN-OUT */
	public static void Tro(int[] a, int[] p) {
	  System.out.println("Currently at Tro ");  
	}
	
	/* SUPERVISOR CALL FROM USER PROGRAM */
	public static void Svc(int[] a, int[] p) {
      System.out.println("Currently at SVC job wants to: "+a[0]);       
	}	

}
