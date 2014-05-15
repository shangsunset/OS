/*import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class MemoryManager {
	 public  LinkedList<int[]> memory;
	 public int location;
	 //private DrumManager drum;
	 public MemoryManager(){//DrumManager drum) {
		 memory= new LinkedList<int[]>();
		 int []init =  {0, 100};
		 //for(int i=0; i<100; i++)
			memory.add(init);
		// this.drum = drum;
	 }
	 
	 
	 public int find_space(int size){
		 ListIterator<int[]> itr = memory.listIterator();
		   while(itr.hasNext()==true)
		    {
			
		     int []entry = itr.next();
			 
		     if(entry[1] > size) {
			 //System.out.println(entry[1]);
			 //adds job
		    	 location = entry[0];
		    	 entry[0] += size;
				 System.out.println("entry[0]: " +entry[0]);
		    	 entry[1] -= size;
				 System.out.println("entry[1]: " +entry[1]);
		    	 return location;
		     }
		    }
			//no values in mem
		   return -1;
	 }

	 public void removeJob(int location, int size){
		 //ListIterator<int[]> itr = memory.listIterator();
		 boolean spaceFreed = false;

		  // if (!spaceFreed) {
			   ListIterator<int[]> itr1 = memory.listIterator();
			   while(spaceFreed == false && itr1.hasNext()) {
				   int []entry = itr1.next();
				   //itr1.next();
				   if(entry[0] > location) {
				   System.out.println("OLD "+entry[0] +" "+entry[1]+" "+ location);
					   entry = itr1.previous();
					   System.out.println("OLD2 "+entry[0] +" "+entry[1]+" "+ location);
					   int newEntry[] = {location, size};
					   System.out.println("NEW "+newEntry[0] +" "+ newEntry[1]);
					   itr1.add(newEntry);
					   spaceFreed = true;
				   }
				   
			   //}
		   }
		   joinEntries();
	 }
	 public void displayMemoryTable() {
		 //System.out.println("++++++++++++++++++++++");
		 //System.out.println("| location \t| size \t|");

		 //for(int[] entry : memory) {
			// System.out.println("| " + entry[0] + "\t\t| " + entry[1] + "\t|");
		// }
		 //System.out.println("++++++++++++++++++++++");

	 }
	 public void joinEntries() {
	 //System.out.println("ENTRY JOB");
		 ListIterator<int[]> itr = memory.listIterator();
		 int[] lastEntry=itr.next();;
		   while(itr.hasNext())
		    {
			 if(lastEntry == null) {
				  //lastEntry = itr.next();
				 //continue;
			  }
		     int []entry = itr.next();
		     if(lastEntry[0] + lastEntry[1] == entry[0]  ) {
				//System.out.println("last:" +entry[1]);
		    	 lastEntry[1] += entry[1];
		    	 itr.remove();
				 //break;
		     } 
		    }
	 }
	 
}*/


// BEST FIT
public class MemoryManager {
    public int[] memory = new int[100];

    public int find_space(int jobSize, int jobNumber) {
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


    public void removeJob(int start, int size) {
        //set memory spaces that were occupied by job back to 0
        for(int i = start; i<start+size; i++)
            memory[i] = 0;
    }
//
//    public void removeJobFromMemory(Job job, String direction) {
//        job.setDirection(direction);
//
//    }

    public void displayMemoryTable(){
    }
}	 