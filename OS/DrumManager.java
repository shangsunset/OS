import java.util.*;
public class DrumManager {
    public  LinkedList<Job> drum = new LinkedList<Job>();;
    private boolean drumIdle = true;

    public void queue(Job job, int d) {
        job.setDirection(d);
        drum.add(job);
    }

    public void doneSwapping() {
		drumIdle = true;
        Job j = drum.pop();
        if(j.getDirection()==0)
            j.setInMemory(true);
    }
	
    public void swapper(LinkedList<Job> jobTable) {
		Job j;
		for(int i=0; i<jobTable.size(); i++) {
			j = jobTable.get(i);
            if (j.getJobAddress() == -1 ) {
                if(j.findSpace()!=-1){
                    queue(j, 0);
                }
            }
        }
        if(drum.size()>0 ) {
            Job newJob = drum.getFirst();
            if(newJob.getDirection()==0 && drumIdle) {
				drumIdle = false;
                sos.siodrum(newJob.getJobNumber(), newJob.getJobSize(), newJob.getJobAddress(), 0);
            }
        }
    }	
}



