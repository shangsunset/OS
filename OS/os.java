import java.util.*;

class os {
	
	/* INITIALIZATION */
	public static LinkedList<job> jobTable = new LinkedList<job>();

	

	public os() {
		
	}
	
	public static void startup() {
		
		os os = new os();
		
		
		
	
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
		
		
//		job newJob = new job();
//		newJob.setJobNum(p[1]);
//		newJob.setJobPriority(p[2]);
//		newJob.setJobSize(p[3]);
//		newJob.setCPUTime(p[4]);
//		newJob.setCurrentTime(p[5]);
		
		///or
		job newComingJob = new job(p[1], p[2], p[3], p[4], p[5]);
		jobTable.add(newComingJob);
		
	}
	
	/* DISK INTERRUPT */
	public static void Dskint(int[] a, int[] p) {

	}

	/* DRUM INTERRUPT */
	public static void Drmint(int[] a, int[] p) {
	
	}
	
	/* TIMER-RUN-OUT */
	public static void Tro(int[] a, int[] p) {
	
	}
	
	/* SUPERVISOR CALL FROM USER PROGRAM */
	public static void Svc(int[] a, int[] p) {

	}	

}
