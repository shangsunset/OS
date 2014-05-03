import java.util.*;

//RoundRobin
public class CpuScheduler {

    private List<Job> readyQueue;
    private int timeQuantum = 10;
    private boolean isCpuIdle = true;

    public CpuScheduler(Job jobTable) {

        readyQueue = new LinkedList<Job>();

    }


    public void addJobToRQ(Job newJob) {

        readyQueue.add(newJob);
    }

    public void removeJobFromRQ(Job jobToRun) {

        readyQueue.remove(jobToRun);

    }


    public int getTimeQuantum() {
        return timeQuantum;
    }

    public boolean isCpuIdle() {

        return isCpuIdle;
    }

    public void schedule(int[] a, int[] p) {

        while(readyQueue.size() >0 && isCpuIdle) {

            Job jobToRun = readyQueue.remove(0);
            Collections.rotate(readyQueue, 1);

            a[0] = 2;
            p[2] = jobToRun.getJobAddress();
            p[3] = jobToRun.getJobSize();
            p[4] = timeQuantum;
            isCpuIdle = false;


        }

    }


}
