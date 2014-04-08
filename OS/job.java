import java.util.Date;


public class job {
	
	private long jobNum;
	private long jobPriority;
	private long jobSize;
	private long CPUTime;
	private long currentTime;
	
	public job() {}
	
	public job(long jobNum, long jobPriority, long jobSize, long CPUTime, long currentTime){
		this.jobNum = jobNum;
		this.jobPriority = jobPriority;
		this.jobSize = jobSize;
		this.CPUTime = CPUTime;
		this.currentTime = currentTime;
	}
	
	
	
	public long getJobNum() {
		return jobNum;
	}
	public void setJobNum(long jobNum) {
		this.jobNum = jobNum;
	}
	public long getJobPriority() {
		return jobPriority;
	}
	public void setJobPriority(long jobPriority) {
		this.jobPriority = jobPriority;
	}
	public long getJobSize() {
		return jobSize;
	}
	public void setJobSize(long jobSize) {
		this.jobSize = jobSize;
	}
	public long getCPUTime() {
		return CPUTime;
	}
	public void setCPUTime(long cPUTime) {
		CPUTime = cPUTime;
	}
	public long getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	
	
	
}
