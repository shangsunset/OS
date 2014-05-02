import java.util.*;

class MemoryManager {
	public ArrayList<int[]> memory; //declare list for memory
	public ListIterator<int[]> i; //declare iterator to traverse memory
	public int max[] = {0, 100};
	public int space[]; // only index [0] and [1] are going to be used
	public int free;
	
	public MemoryManager() {
		memory = new ArrayList<int[]>(); //initialize memory
		i = memory.listIterator(); //set iterator to memory
		memory.add(max); //initializes with arrays start at 0 and end 100
	}
	
	
	public int freeSpace(int jobSize) {
		//loop if there are jobs in the list to check or until we find
		//a free space to place a job in
		while(i.hasNext()==true) {
			space = i.next();
			//if free space is greater than the size of job
			if(space[1] > jobSize) { 
				available = space[0];
				//item[0] is end pointer of job
				//eg. for job#1  occupied 18
				space[0] = space[0]+jobSize;
				//is available space
				//available space 82
				space[1] = space[1]-jobSize;
				//return the available free space to place a job in
				return free;
			}
		}
		//return when No free space available
		return -1;
	}
	
	public void remove(int jobSize, int jobAddress) {
		//jobAddress is the start of the job to be deleted
		while(true) {
			space = i.next();
			//space[0] gives us the first job found with endpointer 
			//greater than the the starting address of the job to be deleted
			if(space[0] > jobAddress) {
				//free up the space
				i.previous();
				int newSpace[] = {jobAddress, size};
				i.add(nextSpace);
				//space in the table has been freed
				//we exit the loop
				break;
			}
		}
		
		//holds the job spaces to be erased/merged
		int prevSpace[] = i.next();
		while(i.hasNext() == true) {
			//space holds each job space in memory
			//eg job#1 = space 82
			space = i.next();
			//when space holds the memory space next to the previous
			//job that is free, we merge them together
			//and remove it.
			//eg. job#1 prevSpace[0] = 0 , prevSpace[1] = 18
			//the free space available next to it is space[0]
			//which starts at 18, total free space[1] = 82
			//so after adding we get 100 free space back
			if(prevSpace[0] + prevSpace[1] == space[0]) {
				prevSpace[1] = prevSpace[1]+space[1];
				i.remove();
			}
		}
	}
	
}