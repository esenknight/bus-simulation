// boarding, deboarding, and traveling events for standard buses

public class BusEvent implements Event{

  private int currentStop;
  private Bus currentBus;
  private int line;
  private int nextBusEventTime;
  private Rider tempRider;

  public BusEvent(int stop, Bus bus, int l){
    currentStop = stop;
    currentBus = bus;
    line = l;
    nextBusEventTime = 0;
  } // BusEvent

  public BusEvent(int stop, Bus bus, int l, int nextEventTime){
    currentStop = stop;
    currentBus = bus;
    line = l;
    nextBusEventTime = nextEventTime;
  } // BusEvent alt constructor for case 1 in run()

  public String toString(){
    String theString = "Bus Event :: ";
    theString += " Current Stop: " + currentStop;
    theString += " Current Time: " + BusSim.agenda.getCurrentTime();
    return theString;
  } // toString

  // simulates deboarding (case 0), boarding (case 1/2/3), and traveling (case 3) of bus
  public void run(){
    switch (line){
      case 0: // Riders who have reached destinationStop now exiting bus
             Rider[] removedRiders = currentBus.removeRidersAtStop(currentStop);
             nextBusEventTime = 2 * removedRiders.length;

             //stats gathreing
             for (int i = 0; i < removedRiders.length; i++){
               BusSim.stats.updateMaxTravelTime(BusSim.agenda.getCurrentTime() - removedRiders[i].getArrivalTime());
               BusSim.stats.updateMeanTravelTime(BusSim.agenda.getCurrentTime() - removedRiders[i].getArrivalTime());
             } // for

             if (nextBusEventTime < 15){
               BusSim.agenda.add(new BusEvent(currentStop, currentBus,1,nextBusEventTime), nextBusEventTime);
             } // if
             else {
               BusSim.agenda.add(new BusEvent(currentStop,currentBus,2), nextBusEventTime);
             } // else
             //System.out.print(this); // testing purposes
             //System.out.println(" Begin Boarding in: " + nextBusEventTime); // testing purposes
             break;
      case 1: // few to no Riders exited, may have the special case of just waiting 15 sec
             int temp = nextBusEventTime;
             nextBusEventTime = 0;
             // new Riders boarding bus
             while (!BusSim.getStop(currentStop).isEmpty() && !currentBus.isFull()){
               tempRider = (Rider) BusSim.getStop(currentStop).removeRider();
               currentBus.addRider(tempRider);

               // stats gathering
               BusSim.stats.updateMaxWaitTime(BusSim.agenda.getCurrentTime() - tempRider.getArrivalTime());
               BusSim.stats.updateMeanWaitTime(BusSim.agenda.getCurrentTime() - tempRider.getArrivalTime());

               nextBusEventTime += 3;
             } // while
             if (temp + nextBusEventTime < 15){ // ensure spending atleast 15 sec at stop
               nextBusEventTime = 15 - temp;
             } // if
             BusSim.agenda.add(new BusEvent(currentStop,currentBus,3),nextBusEventTime);
             //System.out.print(this); // testing purposes
             //System.out.println(" Leave for Next Stop in: " + nextBusEventTime); // testing purposes
             break;
      case 2: // new Riders boarding bus
             while (!BusSim.getStop(currentStop).isEmpty() && !currentBus.isFull()){
               tempRider = (Rider) BusSim.getStop(currentStop).removeRider();
               currentBus.addRider(tempRider);

               // stats gathering
               BusSim.stats.updateMaxWaitTime(BusSim.agenda.getCurrentTime() - tempRider.getArrivalTime());
               BusSim.stats.updateMeanWaitTime(BusSim.agenda.getCurrentTime() - tempRider.getArrivalTime());

               nextBusEventTime += 3;
             } // while
             BusSim.agenda.add(new BusEvent(currentStop,currentBus,3),nextBusEventTime);
             //System.out.print(this); // testing purposes
             //System.out.println(" Leave for Next Stop in: " + nextBusEventTime); // testing purposes
             break;
      case 3: // boarding any passengers who arrived during intial boarding
             while (!BusSim.getStop(currentStop).isEmpty() && !currentBus.isFull()){
               tempRider = (Rider) BusSim.getStop(currentStop).removeRider();
               currentBus.addRider(tempRider);

               // stats gathering
               BusSim.stats.updateMaxWaitTime(BusSim.agenda.getCurrentTime() - tempRider.getArrivalTime());
               BusSim.stats.updateMeanWaitTime(BusSim.agenda.getCurrentTime() - tempRider.getArrivalTime());

               nextBusEventTime += 3;
             } // while

             // stats gathering
             BusSim.stats.updateMaxBusCapacityUtilization(currentBus.size());
             BusSim.stats.updateMeanBusCapacityUtilization(currentBus.size());

             //System.out.print(this); // testing purposes

             // traveling to next stop
             currentStop++;
             if (currentStop == 30){
               currentStop = 0;
             } // if
             nextBusEventTime += 240;
             BusSim.agenda.add(new BusEvent(currentStop,currentBus,0),nextBusEventTime);
             //System.out.println(" Arrive at Next Stop in: " + nextBusEventTime); // testing purposes
             break;
      default:; // should not get here
    } // switch
  } // run

} // BusEvent
