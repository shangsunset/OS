public class Job {
    private int jobNumber;
    private int jobPriority;
    private int jobSize;
    private int jobAddress;
	private int maxCpuTime;
    private int cpuTimeUsed;
	private int direction;
	private boolean inMemory = false;
    private boolean isBlocked = false;
    private boolean inIO = false;
	private boolean finished = false;
	private boolean latched = false;
	private MemoryManager memory;

    public Job() {}

    public Job(MemoryManager m, int p[]) {
		memory = m;
        jobNumber = p[1];
        jobPriority = p[2];
        jobSize = p[3];
        maxCpuTime = p[4];
        cpuTimeUsed = 0;
	}
	
	public int getJobNumber() {
        return jobNumber;
    }

    public int getJobPriority() {
        return jobPriority;
    }


    public int getJobSize() {
        return jobSize;
    }
	
	public int getJobAddress() {
        return jobAddress;
    }
	
	public int getMaxCpuTime() {
        return maxCpuTime;
    }
	
	public int getCpuTimeUsed() {
        return cpuTimeUsed;
    }
	 
    public void updateCpuTimeUsed(int currentTime, int time) {
        cpuTimeUsed = cpuTimeUsed + (currentTime-time);
    }
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int d) {
		direction = d;
	}
	
	public boolean isInMemory() {
		return inMemory;
	}
	
	public void setInMemory(boolean b) {
		inMemory = b;
	}
	
	public void setBlocked(boolean b) {
		isBlocked = b;
	}

    public boolean isBlocked() {
        return isBlocked;
    }
	
	public boolean isInIO() {
        return inIO;
    }

	public void setInIO(boolean b) {
		inIO = b;
	}
	
	public int findSpace() {
		jobAddress = memory.bestFit(jobSize, jobNumber);
		return jobAddress;
	}
	
	public void setFinished(boolean b) {
		finished = b;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public boolean isLatched() {
		return latched;
	}
	
	public void setLatched(boolean b) {
		latched = b;
	}
	
}


