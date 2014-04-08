import java.util.*;

class os {
	
	/* INITIALIZATION */
	
	public os() {

	}
	
	public static void startup() {
	
	}

	/*	
	public static void siodisk(int jobnum);

	public static void siodrum(int jobnum, int jobsize, int coreaddress, int direction);

	void ontrace();

	void offtrace();
	*/

	/* INTERRUPT HANDLERS */

	/* INDICATES ARRIVAL OF A NEW JOB ON THE DRUM */
	public static void Crint(int[] a, int[] p) {
	
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
