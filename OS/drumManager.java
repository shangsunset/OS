import java.util.LinkedList;

public class drumManager {

    public LinkedList<job> drumQueue;
    public LinkedList<job> jobTable;

    public drumManager(LinkedList<job> jobTable) {

        drumQueue = new LinkedList<job>();
        this.jobTable = jobTable;
    }

    public void swapper(job job, int transferDir) {


        switch (transferDir) {

            //drum to memory
            case 0:
                sos.siodrum(job.getJobNum(), job.getJobSize(), job.getJobAddress(), 0);
                removeFromDrumQueue(job);
                break;

            //memory to drum
            case 1:
                sos.siodrum(job.getJobNum(), job.getJobSize(), job.getJobAddress(), 1);
                addToDrumQeueu(job);
                break;
        }


    }

    public void addToDrumQeueu(job job) {
        drumQueue.add(job);
    }

    public void removeFromDrumQueue(job job) {
        drumQueue.remove(job);
    }
}
