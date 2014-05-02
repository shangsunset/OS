import java.util.*;

class MemoryManager {
	public ArrayList<int[]> memory = new ArrayList<int[]>();
	public ListIterator<int[]> i;
	public int maxMem;
	public int item[];
	public boolean free = false;
	
	public MemoryManager() {
		for(int j=0; j<100; j++)
			memory.add(j);
		i = memory.listIterator();
	}
	
	public int freeSpace(int size) {
		int available;
		while(i.hasNext()==true) {
			item = i.next();
			if(item[1] > size) {
				available = item[0];
				item[0] = item[0]+size;
				item[1] = item[1]-size;
				return available;
			}
		}
		return -1;
	}
	
	public void removeJob(int jobSize, int jobAddress) {
		while(i.hasNext() == true) {
			if(free == false) {
				item = i.next();
				if(item[0] > jobAddress) {
					i.previous();
					int newItem[] = {jobAddress, size};
					free = true;
				}
			else 
				break;
			}
		}
		prevItem = null;
		while(i.hasNext() == true) {
			if(prevItem == null) 
				prevItem = i.next();
			item = i.next();
			if(prevItem[0] + prevItem[1] == item[0]) {
				prevItem[1] = prevItem[1]+item[1];
				i.remove();
			}
		}
	}
	
}