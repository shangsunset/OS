import java.util.ArrayList;
import java.util.LinkedList;


public class DrumManager {
    public  LinkedList<Job> drumQueue;
    public  MemoryManager memory;
    public ArrayList<Job> jobs;
    private boolean isDrumBusy = false;


    public DrumManager(ArrayList<Job> jobs){
        drumQueue = new LinkedList<Job>();
        this.jobs = jobs;
    }

    public boolean isDrumBusy() {
        return isDrumBusy;
    }

    public void setDrumBusy(boolean drumBusy) {
        isDrumBusy = drumBusy;
    }

    public Job queueJob(Job job, String direction) {
        job.setDirection(direction);
        drumQueue.add(job);
        return job;

    }

    public void removeJobFromMemory(Job job, String direction) {
        job.setDirection(direction);
        drumQueue.add(job);


    }

    public void swapped(ArrayList<Job> jobs) {
        setDrumBusy(false);

        Job job = drumQueue.pop();
        if(job.getDirection().equalsIgnoreCase("in")) {
            System.out.println("~~~~~~swapped into memory~~~~~~~~~~~~");
            job.setInMemory(true);
        }
        if(job.getDirection().equalsIgnoreCase("out")) {
            job.getMemory().removeJob(job.getLocation(), job.getSize());
            job.setLocation(-1);
            job.setInMemory(false);
            System.out.println("@@@@@@@@@ out ");

        }
//       return job;

    }



    public Job swapper(Job drumJob) {
        for (Job j : jobs) {
            if (j.getLocation() == -1 ) {
//               System.out.println("job #" + j.getNumber() + " needs a memory location. \n it is of size" + j.getSize());
                if(j.findMemoryLocation()){
                    //System.out.println("found memory location at " +j.getLocation() + "for size " + j.getSize());
                    queueJob(j, "in");
                    System.out.println("////queue job in drum");


                    return j;
                }
                else {


                    for(Job jj:jobs) {
                        if (jj.isInMemory() && jj.getPriority() > 2 && !isDrumBusy() && !jj.isLatched()) {
                            System.out.println("||||||||before marked out");
                            System.out.println("___________job num: " + jj.getNumber() + " job priority: " + jj.getPriority() + " location: " + jj.getLocation() + " size: " + jj.getSize());
                            removeJobFromMemory(jj, "out");
                            setDrumBusy(true);
                            sos.siodrum(jj.getNumber(),jj.getSize(),jj.getLocation(),1);

                            System.out.println("||||||||after marked out");

                        }


                    }
                }
            }
        }
        if( drumQueue.size() > 0 && drumQueue.getFirst() != drumJob) {
            Job newJob = drumQueue.getFirst();
            if(newJob.getDirection().equalsIgnoreCase("in")) {

//                 if(!isDrumBusy()) {
                System.out.println("++++++++ before swapping in +++++++++++");
                System.out.println("___________job num: " + newJob.getNumber() + " job priority: " + newJob.getPriority() + " location: " + newJob.getLocation() + " size: " + newJob.getSize());

                setDrumBusy(true);
                sos.siodrum(newJob.getNumber(),newJob.getSize(),newJob.getLocation(),0);
                System.out.println("-----after swapping in-----");


//                 }


            }



            return newJob;
        }




        return drumJob;
    }

    public void displayDrumQueue() {
        System.out.println("Drum Queue looks like :");
        for(Job d : drumQueue) {


            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("| Job # " + d.getNumber() + "\t\t\t\t\t\t|");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }

}



