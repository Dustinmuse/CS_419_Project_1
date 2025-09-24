import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO: implement the SRTF (Shortest Remaining Time First) scheduling algorithm.
 *
 * SRTF is also known as preemptive SJF
 */

public class SRTF extends Algorithm{

    // The ready queue
    private final PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getRemainingTime));

    // Processes that have not yet arrived
    private final Queue<Process> processesToArrive;

    // The simulation clock
    private int now = 0;

    public SRTF(List<Process> allProcesses){
        super(allProcesses);
        processesToArrive = new LinkedList<>(allProcesses);
    }

    @Override
    public void schedule() {
        System.out.println("Shortest Job First Preemptive:");

        Process currentProcess = null;

        while (!readyQueue.isEmpty() || !processesToArrive.isEmpty() || currentProcess != null) {

            // advance time if nothing is ready
            if (readyQueue.isEmpty() && currentProcess == null) {
                Process nextArrival = processesToArrive.remove();
                if (now < nextArrival.getArrivalTime()) {
                    now = nextArrival.getArrivalTime();
                }
                readyQueue.add(nextArrival);
            }

            // add all new arrivals
            while (!processesToArrive.isEmpty() &&
                    processesToArrive.peek().getArrivalTime() <= now) {
                readyQueue.add(processesToArrive.remove());
            }

            // put process back in queue
            if (currentProcess != null && currentProcess.getRemainingTime() > 0) {
                readyQueue.add(currentProcess);
                currentProcess = null;
            }

            // pick next process automatically (shortest remaining time at head)
            if (!readyQueue.isEmpty()) {
                currentProcess = readyQueue.poll();
            }

            if (currentProcess != null) {
                // Run for 1 second
                System.out.print("At time " + now + ": ");
                CPU.run(currentProcess, 1);
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                now++;

                // finished processes go here
                if (currentProcess.getRemainingTime() == 0) {
                    currentProcess.setFinishTime(now);
                    currentProcess = null;
                }
            }
        }
    }
}
