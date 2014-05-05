// BEST FIT
public class memoryManager {
	public int[] memory = new int[100];
	
	public int bestFit(int jobSize, int jobNumber) {
		int freeSpace = 0;
		int start = -1;
		int bestStart = -1;
		int bestFit = 101;
		int i;
		//Iterate over the whole 100 memory table
		for(i=0; i<100; i++) {
			//when memory[i] is "0" -- it means the space is free
			if(memory[i] == 0) {
				//first 0 we find we make it our start
				if(freeSpace==0) {
					start=i;	
				}
				//increment our freeSpace for every free memory we find
				//next to our start
				freeSpace++;
			}
			//when we find a space that is not free (any other than 0)
			else { 
				//if we found freeSpaces we --
				if(freeSpace>0) {
					//check if the new freeSpaces are greater than job size 
					//AND
					//check if the new freeSpace is less than the BestFit
					if(freeSpace>=jobSize && freeSpace<bestFit) {
						//if true save to the best values
						bestStart=start;
						bestFit=freeSpace;
					}
					//set freeSpace back to 0, to check the rest of the
					//freeSpaces left in memory (if there is)
					freeSpace=0;
				}
			}			
		}
		
		//for when the above loop's "else" and "if" condition is not reached
		if(freeSpace>=jobSize && freeSpace<bestFit) {
			bestStart=start;
		}
		
		//Fill the table with the job number that is occupying their 
		//respective spaces in memory.
		if(bestStart!=-1)
			for(i=bestStart; i<bestStart+jobSize; i++)
				memory[i]=jobNumber;
				
		//returns the location in memory where a job is to be placed.
		//OR -1 if there's no place to place the job
		return bestStart;
	}
	
	
	public void remove(int start, int size) {
		//set memory spaces that were occupied by job back to 0
		for(int i = start; i<start+size; i++)
			memory[i] = 0;
	}
	
	public void displayMemoryTable(){
	}
}	 