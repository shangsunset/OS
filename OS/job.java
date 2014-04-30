import java.util.Date;

public class job {
	private int jobNum;
	private int jobPriority;
	private int jobSize;
	private int CpuTime;
	private int currentTime;
	private int jobAddress;
	private boolean blocked = false;
	private boolean inCore = false;
	private boolean inDrum = false;
	private boolean inCpu = false;
	private boolean inIO = false;
	
	
	public job() {}
	
   	public job(int address, int p[]) {
      		jobAddress = address;
      		jobNum = p[1];
      		jobPriority = p[2];
      		jobSize = p[3];
      		CpuTime = p[4];
      		currentTime = 0;
   	}
	
	public void setInIO(boolean b) {
		inIO = b;
	}
	
	public boolean getInIO() {
		return inIO;
	}
	
	public void setInCpu(boolean b) {
		inCpu = b;
	}
	
	public boolean getInCpu() {
		return inCpu;
	}
	
	public void setInDrum(boolean b) {
		inDrum = b;
	}
	
	public boolean getInDrum() {
		return inDrum;
	}
	
	public void setInCore(boolean b) {
		inCore = b;
	}
	
	public boolean getInCore() {
		return inCore;
	}
	
	public void setBlock(boolean b) {
		blocked = b;
	}
	public boolean getBlock() {
		return blocked;
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

