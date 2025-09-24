# CS_419_Project_1 — CPU Scheduling Algorithms Simulator

This Java project simulates several classic CPU scheduling algorithms:

- **FCFS** — First Come First Served
- **SJF (Non-Preemptive)** — Shortest Job First (no preemption)
- **SRTF (Preemptive SJF)** — Shortest Remaining Time First
- **RR (Round Robin)** — Time-slice based scheduling

The simulator takes a list of processes (arrival time, burst time, etc.), schedules them according to the selected algorithm, and prints execution timelines and metrics such as waiting time and turnaround time.

---

## Overview

This project is for **CS 419 Project 1** and demonstrates how different CPU scheduling policies behave. 
Each algorithm is implemented as a separate class extending a common `Algorithm` base class. 
The main driver reads process data, runs the chosen algorithm, and displays results.

---

## Features

- Implements **FCFS**, **SJF Non-Preemptive**, **SRTF**, and **Round Robin** scheduling
- Handles processes with varying arrival times
- Shows step-by-step CPU execution (“Gantt chart”-style output)
- Calculates per-process and average metrics (finish time, turnaround time, waiting time)
- Easy to add more scheduling algorithms thanks to the `Algorithm` base class

---

## Getting Started

### Prerequisites

- Java 15 or later
- A command line or IDE (IntelliJ, Eclipse, VS Code, etc.)

---

### Project Structure

Inside the `src` folder you’ll find:

```text
src/
├── Algorithm.java # abstract base class for scheduling algorithms
├── FCFS.java # First Come First Served
├── SJF.java # Shortest Job First (non-preemptive)
├── SRTF.java # Shortest Remaining Time First (preemptive SJF)
├── RR.java # Round Robin
├── Process.java # defines a process (arrival time, burst time, etc.)
├── CPU.java # simulates running a process for one time unit
└── Driver.java # runs the simulation
```

---

### How to Run

1. Clone this repository:

   ```bash
   git clone https://github.com/Dustinmuse/CS_419_Project_1.git
   cd CS_419_Project_1/src
   ```

2. You will need a text file in the root of the project with how many processes you want to use in this format: 
processId, arrivalTime, burstTime (example: P2, 1, 4)

3. Compile the source:

   ```bash
   javac -d bin *.java
   ```
   
4. Run the driver:

   ```bash
   java -cp bin Driver
   ```
   
---
   
### How Each Algorithm Works

Scheduling Algorithms:

- FCFS (First Come First Served)
  - Runs processes in the order they arrive.

- SJF (Non-Preemptive)
  - Always picks the shortest next job, but once a job starts it runs to completion.

- SRTF (Shortest Remaining Time First)
  - Preemptive version of SJF - at every unit of time, the currently running job may be interrupted if a shorter remaining job arrives.

- Round Robin (RR)
  - Each process gets a fixed time quantum in rotation; preemption occurs at the end of each quantum.