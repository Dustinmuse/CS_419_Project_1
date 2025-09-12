import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO: implement the RR (Round Robin) scheduling algorithm
 */
public class RR extends Algorithm{

    // The ready queue
    private final Queue<Process> readyQueue = new LinkedList<>();

    // Processes that have not yet arrived
    private final Queue<Process> processesToArrive;

    // The simulation clock
    private int now = 0;

    private final int quantumTime = 5;

    public RR(List<Process> allProcesses){
        super(allProcesses);
        processesToArrive = new LinkedList<>(allProcesses);
    }

    @Override
    public void schedule(){
        System.out.println("Round Robin:");

        while (!readyQueue.isEmpty() || !processesToArrive.isEmpty()) {
            if (readyQueue.isEmpty()) {
                Process process = processesToArrive.remove();
                if (now < process.getArrivalTime()) {
                    now = process.getArrivalTime();
                }
                readyQueue.add(process);
            }
            Process currentProcess = readyQueue.remove();

            //setting the clock time | change the 5 to 10 for schedule2.txt
            int quantum;
            if (currentProcess.getRemainingTime() < quantumTime)
            {
                quantum = currentProcess.getRemainingTime();
            } else {
                quantum = quantumTime;
            }

            //runs process until there is no more time remaining for the process to be executed
            if (currentProcess.getRemainingTime() > 0)
            {
                System.out.print("At time " + now + ": ");
                CPU.run(currentProcess, quantum);
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - quantum);
                now += quantum;
                //ends process if there is no more remaining time
                if (currentProcess.getRemainingTime() == 0){
                    currentProcess.setRemainingTime(0);
                    currentProcess.setFinishTime(now);
                }
                //adds process back to the queue if it isn't fully complete
                processesToArrive.add(currentProcess);
            }

            while(!processesToArrive.isEmpty() && processesToArrive.peek().getArrivalTime()<=now){
                readyQueue.add(processesToArrive.remove());
            }
        }
    }
}
