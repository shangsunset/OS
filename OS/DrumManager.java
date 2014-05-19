import java.util.*;
public class DrumManager {
    public  LinkedList<Job> drum = new LinkedList<Job>();;
    private boolean drumIdle = true;

    //add job to drum queue and set the direction
    public void queue(Job job, int d) {
        job.setDirection(d);
        drum.add(job);
    }
    //called in drmint indicating which swapping just been done and drum is idle
    public void doneSwapping() {
		drumIdle = true;
        Job j = drum.pop();
        if(j.getDirection()==0)
            j.setInMemory(true);
    }
	//swaps jobs from drum to memory or memory to drum
    public void swapper(LinkedList<Job> jobTable) {
		Job j;

        //interate the job table, if a job is not in memory, find it memory space, 
        //add it to drum queue, and get it ready to be swapped
		for(int i=0; i<jobTable.size(); i++) {
			j = jobTable.get(i);
            if (j.getJobAddress() == -1 ) {
                if(j.findSpace()!=-1){
                    queue(j, 0);
                }
            }
        }

        //when theres job in drum queue
        if(drum.size()>0 ) {
            Job newJob = drum.getFirst();

            //check direction and if drum is busy
            if(newJob.getDirection()==0 && drumIdle) {
				drumIdle = false;
                sos.siodrum(newJob.getJobNumber(), newJob.getJobSize(), newJob.getJobAddress(), 0);
            }
        }
    }	
}



