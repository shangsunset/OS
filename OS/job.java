

public class job implements Comparable <job> {
	
	private int jobNum;
	private int jobPriority;
	private int jobSize;
	private int CpuTime;
	private int currentTime;
	private int jobAddress;
   
	public job() {}
	
   public job(int address, int p[]) {
      jobAddress = address;
      jobNum = p[1];
      jobPriority = p[2];
      jobSize = p[3];
      CpuTime = p[4];
      currentTime = p[5];
   }

	
   public int getJobAddress() {
         return jobAddress;
   }
	
	public int getJobNum() {
		return jobNum;
	}
	public void setJobNum(int jobNum) {
		this.jobNum = jobNum;
	}
	public int getJobPriority() {
		return jobPriority;
	}
	public void setJobPriority(int jobPriority) {
		this.jobPriority = jobPriority;
	}
	public int getJobSize() {
		return jobSize;
	}
	public void setJobSize(int jobSize) {
		this.jobSize = jobSize;
	}
	public int getCpuTime() {
		return CpuTime;
	}
	public void setCPUTime(int CpuTime) {
		this.CpuTime = CpuTime;
	}
	public int getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

    //for comparing each job's current time when using FCFS
    @Override
    public int compareTo(job other) {

        int comparedCurrentTime = other.getCurrentTime();

        if (this.getCurrentTime() > comparedCurrentTime) {
            return 1;
        }
        else if (this.getCurrentTime() < comparedCurrentTime){
            return -1;

        }
        else
            return 0;


    }
	
}
