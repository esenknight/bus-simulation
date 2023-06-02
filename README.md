# Bus Simulation

## Project Name and Author Information

Project: a simulation of a bus route  
Primary file: BusSim.java  
Secondary files:  Bus.java, BusEvent.java, ExpressBusEvent.java, Rider.java, RiderEvent.java, Stop.java, and Statistics.java  
Supporting files: Event.java, PQInterface.java, PQ.java, Q.java, Q2.java, Segment.java  
Author of primary and secondary files: S. N. Knight  
Author of supporting files: Chris Dovolis  

## Running the Project

Each trial must completed separately.  
1. Set the desired values of the simulation parameters as desired (load, run time, number and type of buses).
2. Alter the title of the file to which the statistics will be written to accordingly. Alternatively, the code that writes to a file may be commented out so that the results are only printed to the terminal.
3. Compile and run the program.

## Project Overview

### BusSim

Contains the instance of Statistics (stats), the PQ (agenda), simulation parameters, etc.   
Runs the simulation and records the results

### Bus

Contains the list of riders in each bus

### BusEvent

Simulates the actions of regular buses: boarding riders, debording riders, departing stops

### ExrpessBusEvent

Simulates the actions of express buses: boarding riders, debording riders, departing stops

### Rider

Contains the boarding stop, destination stop, and arrival time of each rider  
Randomly determines destination stop within the constructor

### RiderEvent

Simulates the arrival of riders to their respective boarding stops

### Stop

Contains the line of riders waiting at each stop

### Statistics

Contains all stats gathered throughout the simulation and performs related calculations

## Utilized Data Structures and Algorithms

### Priority Queue

PQ agenda in BusSim : only needs one instance of each event queued at any given time, priority level keeps track of time

### Queue

Q2 riderList in Bus : efficient to board new riders O(1)  
Q2 riderWaitLine : logically models function of a wait line, efficient rider arrivals O(1), efficient when riders leave to board a regular bus O(1)

### Array

int[] eastboundStopSelect in Rider : finite quantity of data, frequent lookups  
int[] westboundStopSelect in Rider : finite quantity of data, frequent lookups  
double[] arrivalFrequencyVariations : finite quantity of data, frequent lookups

## Known Issues

### Clumping

Over time, buses will gradually drift closer together. This can lead to unequal service along the route and skew results from trials with particularly long run times. To avoid the worst of clumping, the longest trial ran as part of the simulation was 16000s.

### Initial Placement Optimization

Regular buses are initially placed around the route at roughly equidistant stops. However, the algorithm used to do this is biased towards the lower stops (ex. 11 buses will be placed every other stop starting at stop 0). This would occasionally leave a small gap towards one end of the route, possibly resulting in skewed data
