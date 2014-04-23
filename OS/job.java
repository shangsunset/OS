import java.util.Date;

public class job {
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
      		currentTime = 0;
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
	public void setCpuTime(int CpuTime) {
		this.CpuTime = CpuTime;
	}
	public int getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	public void updateCurrentTime(int time, int timeSpentCpu) {
		currentTime = currentTime + (time-timeSpentCpu);
	}	
}
