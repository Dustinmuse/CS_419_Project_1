
import java.util.*;

/**
 * TODO: Implement the non-preemptive SJF (Shortest-Job First) scheduling algorithm.
 */

public class SJF extends Algorithm {
    /*
    At some point I need to make a new queue to input the new ordered list
     */
    private final Queue<Process> readyQueue = new LinkedList<>();

    // Processes that have not yet arrived
    private final Queue<Process> processesToArrive;

    // The simulation clock
    private int now = 0;


    public SJF(List<Process> allProcessList){
        super(allProcessList);
        processesToArrive = new LinkedList<>(allProcessList);//Setup
    }

    @Override
    public void schedule(){
        System.out.println("Shortest Job First (Non-Prementive)");
            /*
            Aight, this works by taking in Process 1, but then Chooseing the next smallest burst time in the processQueue
            This one also can look in the future, it just can't stop half way through a process.

            For this to work we need:
            - Algorithm to order the queue from shortest job first, to longest last
             */

        //Starts reading the processesToArrive list and adding them to readyQueue list
        while(!readyQueue.isEmpty() || !processesToArrive.isEmpty()){ //if either of these are empty
            //keep going
            if(readyQueue.isEmpty()){ //if readyQueue ISNT empty
                Process process = processesToArrive.remove();
                if (now < process.getArrivalTime()) {
                    //advance the simulation clock to the next process's arrival time
                    now = process.getArrivalTime(); //update Clock
                }
                readyQueue.add(process);    //add Process to readyQueue
            }

            List<Process> SJFSortedQueue = new ArrayList<>(readyQueue); //Creating sorted List
            SJFSortedQueue.sort(Comparator.comparingInt(Process::getBurstTime)); //sorting Process via BurstTime

            Process currentProcess = SJFSortedQueue.remove(0);//List ver of removing first item
            readyQueue.remove(currentProcess);

            int runTime = currentProcess.getBurstTime();
            System.out.println("At time: "+ now +": ");
            CPU.run(currentProcess, runTime);

            now+=runTime;

            currentProcess.setRemainingTime(0);
            currentProcess.setFinishTime(now);

            while(!processesToArrive.isEmpty() && processesToArrive.peek().getArrivalTime()<=now){
                readyQueue.add(processesToArrive.remove());
            }

        }








    }
}
