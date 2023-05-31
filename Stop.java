// bus stops with bus rider arrivals and wait lines

public class Stop{

  private Q2 riderWaitLine;

  public Stop(){
    riderWaitLine = new Q2();
  } // Stop

  public Q2 getRiderWaitLine(){
    return riderWaitLine;
  } // getRiderWaitLine

  public int size(){
    return riderWaitLine.length();
  } // size

  // add rider newly arrived at a stop to the stop's wait line
  // @param newRider a Rider who has just arrived to the stop
  public void addRider(Rider newRider){
    riderWaitLine.add(newRider);
  } // addRider

  // removes a rider from the stop wait line once they have boarded a bus
  // @returns the Rider who has left the stop
  public Rider removeRider(){
    return (Rider) riderWaitLine.remove();
  } // removeRider

  // @returns true iff no riders are currently waiting at the stop
  public boolean isEmpty(){
    if (riderWaitLine.length() == 0){
      return true;
    } // if
    else {
      return false;
    } // else
  } // isEmpty
} // Stop
