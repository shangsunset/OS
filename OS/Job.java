
public class Job {
    private int jobNum;
    private int jobPriority;
    private int jobSize;
    private int maxCpuTime;
    private int cpuTimeUsed
    private int CpuTimeLeft;
    private int jobAddress;
	private MemoryManager memory;
    private boolean isBlocked = false;
    private boolean isInCore = false;
    private boolean isInDrum = false;
    private boolean isInCpu = false;
    private boolean isInIO = false;
	private boolean finished = false;
	private boolean remove = false;

    public Job() {}

    public Job(MemoryManager m, int p[]) {
		memory = m;
        jobNum = p[1];
        jobPriority = p[2];
        jobSize = p[3];
        maxCpuTime = p[4];
        cpuTimeUsed = 0;
		findSpace();
    }

	public void findSpace() {
		jobAddress = memory.bestFit(size, number);
	}
	
	public int getJobAddress() {
		return jobAddress;
	}
	
	public void setRemove(boolean b) {
		remove = b;
	}
	
	public boolean toRemove() {
		return remove;
	}
	
	public void setFinished(boolean b) {
		finished = b;
	}
	
	public boolean isFinished() {
		return finished;
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

    public int getCpuTimeLeft() {
		cpuTimeLeft = maxCpuTime - cpuTimeUsed;
        return cpuTimeLeft;
    }

	/*
    public void setCpuTimeLeft(int cpuTimeLeft) {
        CpuTimeLeft = cpuTimeLeft;
    } */

    public int getMaxCpuTime() {
        return maxCpuTime;
    }

    public void updateCpuTimeUsed(int realTime, int currentTime) {
        cpuTimeUsed = cpuTimeUsed + (realTime-currentTime);
    }

    public int getCpuTimeUsed() {
        return cpuTimeUsed;
    }
}


