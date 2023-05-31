// stats collected to evaluate optimal bus quantity and frequency 

public class Statistics{

  // general stats (not associated with bus type)
  private int maxQueueLength;
  private double meanQueueLength;
  // following two used to calculate meanQueueLength
  private double totalQueueLength; // at Rider arrival
  private double ridersQueued;

  // regular buses
  private int maxBusCapacityUtilization;
  private double maxWaitTime;
  private double maxTravelTime;
  private double meanBusCapacityUtilization; // at departure
  private double meanWaitTime;
  private double meanTravelTime;
  // following two used to calculate meanBusCapacityUtilization
  private double totalDepartures;
  private double ridersAtDepartures;
  // following two used to calculate meanTravelTime
  private double totalTravelTime;
  private double ridersDelivered;
  // following two used to calculate meanWaitTime
  private double totalWaitTime;
  private double ridersBoarded;

  // express buses
  private int maxBusCapacityUtilizationExpress;
  private double maxWaitTimeExpress;
  private double maxTravelTimeExpress;
  private double meanBusCapacityUtilizationExpress; // at departure
  private double meanWaitTimeExpress;
  private double meanTravelTimeExpress;
  // following two used to calculate meanBusCapacityUtilizationExpress
  private double totalDeparturesExpress;
  private double ridersAtDeparturesExpress;
  // following two used to calculate meanTravelTime
  private double totalTravelTimeExpress;
  private double ridersDeliveredExpress;
  // following two used to calculate meanWaitTime
  private double totalWaitTimeExpress;
  private double ridersBoardedExpress;

  // both types of buses
  private int maxBusCapacityUtilizationOverall;
  private double maxWaitTimeOverall;
  private double maxTravelTimeOverall;
  private double meanBusCapacityUtilizationOverall; // at departure
  private double meanWaitTimeOverall;
  private double meanTravelTimeOverall;

  public Statistics(){
    maxBusCapacityUtilization = 0;
    maxBusCapacityUtilizationExpress = 0;
    maxBusCapacityUtilizationOverall = 0;

    maxTravelTime = 0;
    maxTravelTimeExpress = 0;
    maxTravelTimeOverall = 0;

    maxQueueLength = 0;

    maxWaitTime = 0;
    maxWaitTimeExpress = 0;
    maxWaitTimeOverall = 0;

    meanBusCapacityUtilization = 0;
    meanBusCapacityUtilizationExpress = 0;
    meanBusCapacityUtilizationOverall = 0;

    ridersAtDepartures = 0;
    ridersAtDeparturesExpress = 0;

    totalDepartures = 0;
    totalDeparturesExpress = 0;

    meanTravelTime = 0;
    meanTravelTimeExpress = 0;
    meanTravelTimeOverall = 0;

    ridersDelivered = 0;
    ridersDeliveredExpress = 0;

    totalTravelTime = 0;
    totalTravelTimeExpress = 0;

    meanWaitTime = 0;
    meanWaitTimeExpress = 0;
    meanWaitTimeOverall = 0;

    ridersBoarded = 0;
    ridersBoardedExpress = 0;

    totalWaitTime = 0;
    totalWaitTimeExpress = 0;

    meanQueueLength = 0;

    ridersQueued = 0;
    totalQueueLength = 0;
  } // Statistics

  public String toString(){
    String theString = "";
    theString += "Max Bus Capacity Utilization, Regular: " + maxBusCapacityUtilization + "\n";
    theString += "Max Bus Capacity Utilization, Express: " + maxBusCapacityUtilizationExpress + "\n";
    theString += "Max Bus Capacity Utilization, Overall: " + maxBusCapacityUtilizationOverall + "\n";
    theString += "\n";
    theString += "Max Travel Time, Regular: " + maxTravelTime + "\n";
    theString += "Max Travel Time, Express: " + maxTravelTimeExpress + "\n";
    theString += "Max Travel Time, Overall: " + maxTravelTimeOverall + "\n";
    theString += "\n";
    theString += "Max Queue Length: " + maxQueueLength + "\n";
    theString += "\n";
    theString += "Max Wait Time, Regular: " + maxWaitTime + "\n";
    theString += "Max Wait Time, Express: " + maxWaitTimeExpress + "\n";
    theString += "Max Wait Time, Overall: " + maxWaitTimeOverall + "\n";
    theString += "\n";
    theString += "Mean Bus Capacity Utilization, Regular: " + meanBusCapacityUtilization + "\n";
    theString += "Mean Bus Capacity Utilization, Express: " + meanBusCapacityUtilizationExpress + "\n";
    theString += "Mean Bus Capacity Utilization, Overall: " + meanBusCapacityUtilizationOverall + "\n";
    theString += "\n";
    theString += "Mean Travel Time, Regular: " + meanTravelTime + "\n";
    theString += "Mean Travel Time, Express: " + meanTravelTimeExpress + "\n";
    theString += "Mean Travel Time, Overall: " + meanTravelTimeOverall + "\n";
    theString += "\n";
    theString += "Mean Wait Time, Regular: " + meanWaitTime + "\n";
    theString += "Mean Wait Time, Express: " + meanWaitTimeExpress + "\n";
    theString += "Mean Wait Time, Overall: " + meanWaitTimeOverall + "\n";
    theString += "\n";
    theString += "Mean Queue Length: " + meanQueueLength + "\n";
    return theString;
  } // toString

  // regular bus functions
  public void updateMaxBusCapacityUtilization(int newMax){
    if (newMax > maxBusCapacityUtilization){
      maxBusCapacityUtilization = newMax;
    } // if
  } // updateNewMaxBusCapacityUtilization

  public void updateMaxTravelTime(double newMax){
    if (newMax > maxTravelTime){
      maxTravelTime = newMax;
    } // if
  } // updateMaxTravelTime

  public void updateMaxWaitTime(double newMax){
    if (newMax > maxWaitTime){
      maxWaitTime = newMax;
    } // if
  } // updateMaxWaitTime

  public void updateMeanBusCapacityUtilization(double riders){
    ridersAtDepartures += riders;
    totalDepartures++;
    meanBusCapacityUtilization = ridersAtDepartures / totalDepartures;
  } // updateMeanBusCapacityUtilization

  public void updateMeanTravelTime(double time){
    ridersDelivered++;
    totalTravelTime += time;
    meanTravelTime = totalTravelTime / ridersDelivered;
  } // updateMeanTravelTime

  public void updateMeanWaitTime(double time){
    ridersBoarded++;
    totalWaitTime += time;
    meanWaitTime = totalWaitTime / ridersBoarded;
  } // updateMeanWaitTime

  // express bus fuctions
  public void updateMaxBusCapacityUtilizationExpress(int newMax){
    if (newMax > maxBusCapacityUtilizationExpress){
      maxBusCapacityUtilizationExpress = newMax;
    } // if
  } // updateNewMaxBusCapacityUtilizationExpress

  public void updateMaxTravelTimeExpress(double newMax){
    if (newMax > maxTravelTimeExpress){
      maxTravelTimeExpress = newMax;
    } // if
  } // updateMaxTravelTimeExpress

  public void updateMaxWaitTimeExpress(double newMax){
    if (newMax > maxWaitTimeExpress){
      maxWaitTimeExpress = newMax;
    } // if
  } // updateMaxWaitTimeExpress

  public void updateMeanBusCapacityUtilizationExpress(double riders){
    ridersAtDeparturesExpress += riders;
    totalDeparturesExpress++;
    meanBusCapacityUtilizationExpress = ridersAtDeparturesExpress / totalDeparturesExpress;
  } // updateMeanBusCapacityUtilizationExpress

  public void updateMeanTravelTimeExpress(double time){
    ridersDeliveredExpress++;
    totalTravelTimeExpress += time;
    meanTravelTimeExpress = totalTravelTimeExpress / ridersDeliveredExpress;
  } // updateMeanTravelTimeExpress

  public void updateMeanWaitTimeExpress(double time){
    ridersBoardedExpress++;
    totalWaitTimeExpress += time;
    meanWaitTimeExpress = totalWaitTimeExpress / ridersBoardedExpress;
  } // updateMeanWaitTimeExpress

  // stats not directly associated with a bus type
  public void updateMaxQueueLength(int newMax){
    if (newMax > maxQueueLength){
      maxQueueLength = newMax;
    } // if
  } // updateMaxQueueLength

  public void updateMeanQueueLength(int length){
    ridersQueued++;
    totalQueueLength += length;
    meanQueueLength = totalQueueLength / ridersQueued;
  } // updateMeanQueueLength

  // overall function for stats about buses of both types
  // compares or combines regular and expres stats to overal statistics
  public void updateOverallStats(){
    // maxBusCapacityUtilizationOverall
    if (maxBusCapacityUtilization > maxBusCapacityUtilizationExpress){
      maxBusCapacityUtilizationOverall = maxBusCapacityUtilization;
    } // if
    else {
      maxBusCapacityUtilizationOverall = maxBusCapacityUtilizationExpress;
    } // else

    //maxTravelTimeOverall
    if (maxTravelTime > maxTravelTimeExpress){
      maxTravelTimeOverall = maxTravelTime;
    } // if
    else {
      maxTravelTimeOverall = maxTravelTimeExpress;
    } // else

    // maxWaitTimeOverall
    if (maxWaitTime > maxWaitTimeExpress){
      maxWaitTimeOverall = maxWaitTime;
    } // if
    else {
      maxWaitTimeOverall = maxWaitTimeExpress;
    } // else

    // meanBusCapacityUtilizationOverall
    meanBusCapacityUtilizationOverall = (meanBusCapacityUtilization + meanBusCapacityUtilizationExpress) / 2;

    // meanTravelTimeOverall
    meanTravelTimeOverall = (meanTravelTime + meanTravelTimeExpress) / 2;

    // meanWaitTimeOverall
    meanWaitTimeOverall = (meanWaitTime + meanWaitTimeExpress) / 2;

  } // updateNewMaxBusCapacityUtilizationOverall

} // Statistics
