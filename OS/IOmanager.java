public class IOmanager {
	 public  LinkedList<Job> IOqueue;

	 public IOmanager(){
		 IOqueue = new LinkedList<Job>();
	 }
	 
	 public Job requestIO(Job job)
	 {
		IOqueue.add(job); //add job requesting IO to IO queue 
	 }
	 
	 public Job IOcompleted(Job job) {
		job.setLatch(false); //IO completed, remove latch bit
		IOQueue.removeLast(); //Remove job from queue		 
	 }
}