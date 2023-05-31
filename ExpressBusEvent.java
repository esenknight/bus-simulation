// boarding, deboarding, and travelling for express buses

public class ExpressBusEvent implements Event{

  private int currentStop;
  private Bus currentBus;
  private int line;
  private int nextBusEventTime;
  private Q2 tempList;
  private Rider temp;
  private int tDS;

  public ExpressBusEvent(int stop, Bus bus, int l){
    currentStop = stop;
    currentBus = bus;
    line = l;
    nextBusEventTime = 0;
    tempList = new Q2();
    tDS = 0;
  } // BusEvent

  public ExpressBusEvent(int stop, Bus bus, int l, int nextEventTime){
    currentStop = stop;
    currentBus = bus;
    line = l;
    nextBusEventTime = nextEventTime;
    tempList = new Q2();
    tDS = 0;
  } // BusEvent alt constructor for case 1 in run()

  public String toString(){
    String theString = "Express Bus Event :: ";
    theString += " Current Stop: " + currentStop;
    theString += " Current Time: " + BusSim.agenda.getCurrentTime();
    return theString;
  } // toString

  // simulates deboarding (case 0), boarding (case 1/2/3), and traveling (case 3) of express bus
  public void run(){
    switch (line){
      case 0: // Riders who have reached destinationStop now exiting bus
             Rider[] removedRiders = currentBus.removeRidersAtStop(currentStop);
             nextBusEventTime = 2 * removedRiders.length;

             //stats gathreing
             for (int i = 0; i < removedRiders.length; i++){
               BusSim.stats.updateMaxTravelTimeExpress(BusSim.agenda.getCurrentTime() - removedRiders[i].getArrivalTime());
               BusSim.stats.updateMeanTravelTimeExpress(BusSim.agenda.getCurrentTime() - removedRiders[i].getArrivalTime());
             } // for

             if (nextBusEventTime < 15){
               BusSim.agenda.add(new ExpressBusEvent(currentStop, currentBus,1,nextBusEventTime), nextBusEventTime);
             } // if
             else {
               BusSim.agenda.add(new ExpressBusEvent(currentStop,currentBus,2), nextBusEventTime);
             } // else
             //System.out.print(this); // testing purposes
             //System.out.println(" Begin Boarding in: " + nextBusEventTime); // testing purposes
             break;
      case 1: // few to no Riders exited, may have the special case of just waiting 15 sec
             int tempTime = nextBusEventTime;
             nextBusEventTime = 0;
             // new Riders boarding bus
             while (!BusSim.getStop(currentStop).isEmpty() && !currentBus.isFull()){
               temp = (Rider) BusSim.getStop(currentStop).removeRider();
               tDS = temp.getDestinationStop();
               if (tDS % 4 == 0 || tDS == 1 || tDS == 14 || tDS == 15 || tDS == 29){ // express destination
                 currentBus.addRider(temp);

                 // stats gathering
                 BusSim.stats.updateMaxWaitTimeExpress(BusSim.agenda.getCurrentTime() - temp.getArrivalTime());
                 BusSim.stats.updateMeanWaitTimeExpress(BusSim.agenda.getCurrentTime() - temp.getArrivalTime());

                 nextBusEventTime += 3;
               } // if
               else { // destination not covered by express route
                 tempList.add(temp);
               } // else
             } // while

             // puting Stop's riderWaitLine back in order, if needed
             while (tempList.length() != 0 && !BusSim.getStop(currentStop).isEmpty()){
               tempList.add(BusSim.getStop(currentStop).removeRider());
             } // while
             while (tempList.length() != 0){
               BusSim.getStop(currentStop).addRider((Rider)tempList.remove());
             } // while

             if (tempTime + nextBusEventTime < 15){ // ensure spending atleast 15 sec at stop
               nextBusEventTime = 15 - tempTime;
             } // if
             BusSim.agenda.add(new ExpressBusEvent(currentStop,currentBus,3),nextBusEventTime);
             //System.out.print(this); // testing purposes
             //System.out.println(" Leave for Next Stop in: " + nextBusEventTime); // testing purposes
             break;
      case 2: // new Riders boarding bus
              while (!BusSim.getStop(currentStop).isEmpty() && !currentBus.isFull()){
                temp = (Rider) BusSim.getStop(currentStop).removeRider();
                tDS = temp.getDestinationStop();
                if (tDS % 4 == 0 || tDS == 1 || tDS == 14 || tDS == 15 || tDS == 29){ // express destination
                  currentBus.addRider(temp);

                  // stats gathering
                  BusSim.stats.updateMaxWaitTimeExpress(BusSim.agenda.getCurrentTime() - temp.getArrivalTime());
                  BusSim.stats.updateMeanWaitTimeExpress(BusSim.agenda.getCurrentTime() - temp.getArrivalTime());

                  nextBusEventTime += 3;
                } // if
                else { // destination not covered by express route
                  tempList.add(temp);
                } // else
              } // while

              // puting Stop's riderWaitLine back in order, if needed
              while (tempList.length() != 0 && !BusSim.getStop(currentStop).isEmpty()){
                tempList.add(BusSim.getStop(currentStop).removeRider());
              } // while
              while (tempList.length() != 0){
                BusSim.getStop(currentStop).addRider((Rider)tempList.remove());
              } // while

             BusSim.agenda.add(new ExpressBusEvent(currentStop,currentBus,3),nextBusEventTime);
             //System.out.print(this); // testing purposes
             //System.out.println(" Leave for Next Stop in: " + nextBusEventTime); // testing purposes
             break;
      case 3: // boarding any passengers who arrived during intial boarding
              while (!BusSim.getStop(currentStop).isEmpty() && !currentBus.isFull()){
                temp = (Rider) BusSim.getStop(currentStop).removeRider();
                tDS = temp.getDestinationStop();
                if (tDS % 4 == 0 || tDS == 1 || tDS == 14 || tDS == 15 || tDS == 29){ // express destination
                  currentBus.addRider(temp);

                  // stats gathering
                  BusSim.stats.updateMaxWaitTimeExpress(BusSim.agenda.getCurrentTime() - temp.getArrivalTime());
                  BusSim.stats.updateMeanWaitTimeExpress(BusSim.agenda.getCurrentTime() - temp.getArrivalTime());

                  nextBusEventTime += 3;
                } // if
                else { // destination not covered by express route
                  tempList.add(temp);
                } // else
              } // while

              // puting Stop's riderWaitLine back in order, if needed
              while (tempList.length() != 0 && !BusSim.getStop(currentStop).isEmpty()){
                tempList.add(BusSim.getStop(currentStop).removeRider());
              } // while
              while (tempList.length() != 0){
                BusSim.getStop(currentStop).addRider((Rider)tempList.remove());
              } // while

              // stats gathering
              BusSim.stats.updateMaxBusCapacityUtilizationExpress(currentBus.size());
              BusSim.stats.updateMeanBusCapacityUtilizationExpress(currentBus.size());

              //System.out.print(this); // testing purposes

             // traveling to next stop
             switch (currentStop){
               case  0:
               case 14:
               case 15:
               case 28: currentStop++;
                        nextBusEventTime += 240;
                        break;
               case 12: currentStop += 2;
                        nextBusEventTime += 480;
                        break;
               case  1: currentStop += 3;
                        nextBusEventTime += 720;
                        break;
               case 29: currentStop = 0;
                        nextBusEventTime += 240;
                        break;
               default: currentStop += 4; // assuming correct initialization, only could be stops 4,8,16,20,24
                        nextBusEventTime += 960;
             } // switch

             BusSim.agenda.add(new ExpressBusEvent(currentStop,currentBus,0),nextBusEventTime);
             //System.out.println(" Arrive at Next Stop in: " + nextBusEventTime); // testing purposes
             break;
      default:; // should not get here
    } // switch
  } // run

} // ExpressBusEvent
