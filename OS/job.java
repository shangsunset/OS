
public class job {
	private int jobNum;
	private int jobPriority;
	private int jobSize;
	private int maxCpuTime;
	private int currentTime;
	private int jobAddress;


   	public job(int address, int p[]) {
      		jobAddress = address;
      		jobNum = p[1];
      		jobPriority = p[2];
      		jobSize = p[3];
      		maxCpuTime = p[4];
      		currentTime = 0;
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
