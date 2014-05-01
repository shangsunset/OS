import java.util.*;

public class os {
	/* INITIALIZATION */
    public static LinkedList<Job> jobTable;
    public static int cpuTimeUsed = 0;
    public static int currentTime = 0;
    public static DrumManager drumManager;
    public static int drumToMemory = 0;
    public static int memoryToDrum = 1;

    public static void startup() {
	  	//sos.ontrace();
        drumManager = new DrumManager(jobTable);
	}

	/*	
	public static void siodisk(int jobnum);
	public static void siodrum(int jobnum, int jobsize, int coreaddress, int direction);
	*/

	/* INTERRUPT HANDLERS */

	/* INDICATES ARRIVAL OF A NEW JOB ON THE DRUM */
	/* SAVE INFORMATION ABOUT NEW COMING JOB */
	public static void Crint(int[] a, int[] p) {

        bookkeeper(p[5]);

        //swap into drum queue
        drumManager.swapper(runningJob, drumToMemory);
	}
   
	//printing on all interrupts to know where the job is at
	
	/* SCHEDULER */
	public static void sched(int[] a, int[] p) { 
	}
   
	/* DISK INTERRUPT */
	public static void Dskint(int[] a, int[] p) {
	}

	/* DRUM INTERRUPT */
	public static void Drmint(int[] a, int[] p) {

        bookkeeper(p[5]);


    }
	
	/* TIMER-RUN-OUT */
	public static void Tro(int[] a, int[] p) {

        bookkeeper(p[5]);


	}
	
	/* SUPERVISOR CALL FROM USER PROGRAM */
	public static void Svc(int[] a, int[] p) {
	}	
	
	public static void bookkeeper(int time){

        int timeDifference = time - cpuTimeUsed;
        runningJob.setCurrentTime(runningJob.getCurrentTime() + timeDifference);
        cpuTimeUsed = time;

	}

}
