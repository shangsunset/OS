import java.util.*;

//RoundRobin
public class CpuScheduler {

    private List<Job> readyQueue;
    private int timeQuantum = 10;
    private boolean isCpuIdle = true;


    public CpuScheduler() {

        readyQueue = new LinkedList<Job>();


    }


    public void addJobToReadyQueue(Job newJob) {

        readyQueue.add(newJob);
    }




    public int getTimeQuantum() {
        return timeQuantum;
    }

    public void setCpuIdle(boolean cpuIdle) {
        isCpuIdle = cpuIdle;
    }

    public boolean isCpuIdle() {

        return isCpuIdle;
    }

    public void schedule(int[] a, int[] p) {

        //as long as there is job in ready queue and CPU is idle, send job to CPU
        while(readyQueue.size() >0 && isCpuIdle) {
            //remove the job about to run from ready queue
            jobToRun = readyQueue.remove(0);

            if(readyQueue.size() > 0) {
                //rotate the queue to point to next job. it will be next jobToRun after current jobToRun finishes
                Collections.rotate(readyQueue, 1);
            }

            run(jobToRun, a, p);
        }

    }



    public void run(Job jobToRun, int[] a, int[]p) {

        //send jobToRun to CPU
        a[0] = 2;
        p[2] = jobToRun.getJobAddress();
        p[3] = jobToRun.getJobSize();
        p[4] = timeQuantum;

        //after sending a process to CPU, set CPU to be busy
        isCpuIdle = false;
    }





}
