import java.util.LinkedList;

public class DrumManager {

    public LinkedList<Job> drumQueue;
    public LinkedList<Job> jobTable;

    public DrumManager(LinkedList<Job> jobTable) {

        drumQueue = new LinkedList<Job>();
        this.jobTable = jobTable;
    }

    public void swapper(Job job, int transferDir) {


        switch (transferDir) {

            //drum to memory
            case 0:
                if(drumQueue.size() > 0) {
                sos.siodrum(job.getJobNum(), job.getJobSize(), job.getJobAddress(), 0);
                removeFromDrumQueue(job);
                }
                break;

            //memory to drum
            case 1:
                sos.siodrum(job.getJobNum(), job.getJobSize(), job.getJobAddress(), 1);
                addToDrumQeueu(job);
                break;
        }


    }

    public void addToDrumQeueu(Job job) {
        drumQueue.add(job);
    }

    public void removeFromDrumQueue(Job job) {
        drumQueue.remove(job.getJobNum());
    }
}
