// bus

public class Bus{

  private final int MAX_CAPACITY;
  private Q2 riderList;

  public Bus(){
    MAX_CAPACITY = 50;
    riderList = new Q2();
  } // Bus

  public int size(){
    return riderList.length();
  } // size

  // adds new rider to bus during boarding
  // @param newRider a rider who wishes to board the bus
  // @returns true iff boarding successful
  public boolean addRider(Rider newRider){
    if (!isFull()){
      riderList.add(newRider);
      return true;
    } // if
    return false;
  } // addRider

  // removes rider from a bus during deboarding
  // @param currentStop an int representing the stop at which the deboarding is taking place
  // @returns array containing all riders deboarded
  public Rider[] removeRidersAtStop(int currentStop){
    Q2 tempList = new Q2();
    Rider temp = null;
    Rider[] removedRiders = new Rider[50];
    Rider[] removedRidersFinal; // length will == number of Riders who exited at this stop
    int nextOpen = 0;
    while (riderList.length() > 0){
      temp = (Rider) riderList.remove();
      if (temp.getDestinationStop() == currentStop){ // Rider exiting bus
        removedRiders[nextOpen] = temp;
        nextOpen++;
      } // if
      else { // Rider waiting for subsiquent stop
        tempList.add(temp);
      } // else
    } // while
    while (tempList.length() > 0){
      riderList.add(tempList.remove());
    } // while
    removedRidersFinal = new Rider[nextOpen];
    for (int i = 0; i < nextOpen; i++){
      removedRidersFinal[i] = removedRiders[i];
    } // for
    return removedRidersFinal;
  } // removeRiders

  // @returns true iff bus is at capactiy
  public boolean isFull(){
    if (riderList.length() >= 50){
      return true;
    } // if
    else {
      return false;
    } // else
  } // isFull



} // Bus
