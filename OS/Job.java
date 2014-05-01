
public class Job {
    private int jobNum;
    private int jobPriority;
    private int jobSize;
    private int maxCpuTime;
    private int currentTime;
    private int jobAddress;
    private boolean isBlocked = false;
    private boolean isInCore = false;
    private boolean isInDrum = false;
    private boolean isInCpu = false;
    private boolean isInIO = false;


    public Job() {}

    public Job(int address, int p[]) {
        jobAddress = address;
        jobNum = p[1];
        jobPriority = p[2];
        jobSize = p[3];
        maxCpuTime = p[4];
        currentTime = 0;
    }


    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isInCore() {
        return isInCore;
    }

    public boolean isInDrum() {
        return isInDrum;
    }

    public boolean isInCpu() {
        return isInCpu;
    }

    public boolean isInIO() {
        return isInIO;
    }

    public int getJobAddress() {
        return jobAddress;
    }

    public int getJobNum() {
        return jobNum;
    }

    public int getJobPriority() {
        return jobPriority;
    }

    public int getJobSize() {
        return jobSize;
    }

    public int getmaxCpuTime() {
        return maxCpuTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void updateCurrentTime(int time, int cpuTimeUsed) {
        currentTime = currentTime + (time-cpuTimeUsed);
    }
}


