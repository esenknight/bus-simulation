// arrival at bus stop event

import java.util.Random;

public class RiderEvent implements Event{

  private int boardingStop;
  private int arrivalFrequency;
  private double[] arrivalFrequencyVariations = {.75,.75,.5,.5,.5,.2,.2,.2,.2,0,0,-.2,-.2,-.2,-.2,-.5,-.5,-.5,-.75,-.75,-.75};

  public RiderEvent(int stopOfBoarding, int frequencyOfArrival){
    boardingStop = stopOfBoarding;
    arrivalFrequency = frequencyOfArrival;
  } // RiderEvent

  public String toString(){
    String theString = "Rider Event :: ";
    theString += "Boarding Stop: " + boardingStop;
    theString += " Current Time: " + BusSim.agenda.getCurrentTime();
    return theString;
  } // toString

  // simulating rider arrival at stop
  public void run(){
    BusSim.getStop(boardingStop).addRider(new Rider(boardingStop)); // add new Rider to the wait line

    //stats gathering
    BusSim.stats.updateMaxQueueLength(BusSim.getStop(boardingStop).size());
    BusSim.stats.updateMeanQueueLength(BusSim.getStop(boardingStop).size());

    Random indexGenerator = new Random();
    double nextRiderEventTime = arrivalFrequency + (arrivalFrequency * arrivalFrequencyVariations[indexGenerator.nextInt(20)]);
    BusSim.agenda.add(new RiderEvent(boardingStop,arrivalFrequency),nextRiderEventTime);
    //System.out.print(this); // testing purposes
    //System.out.println(" Next Rider in: " + nextRiderEventTime); // testing purposes
  } // run

} // RiderEvent
