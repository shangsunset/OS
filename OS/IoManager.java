import java.util.*;

public class IoManager {
	public LinkedList<Job> io = new LinkedList<Job>();
	
	//places a job into io queue if there is a job doing io
	//otherwise send it to do disk io
	public void addToIO(Job job) {
		io.add(job);
		job.setInIO(true);
		
		//Do the requested IO for job if there are no other jobs waiting in the queue
		if(io.size() == 1) {									
			job.setLatched(true);								
			sos.siodisk(job.getJobNumber());					
		} 
	}
	
	public void removeIO() {
		//IO completed for job at top of queue, remove job from queue and unblock it
		Job j = io.get(0);										 
		io.remove(j);
		if(!io.contains(j))
			j.setBlocked(false);
		j.setInIO(false);
		j.setLatched(false);
		
		//Do IO for next job waiting in the queue
		if(io.size() > 0) {										
			io.get(0).setLatched(true);
			sos.siodisk(io.get(0).getJobNumber());
		}		
	}
}
