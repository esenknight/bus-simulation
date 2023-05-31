// bus passenger

import java.util.Random;

public class Rider{

  private double arrivalTime;
  private int boardingStop;
  private int destinationStop;

  private static int[] eastboundStopSelect = {1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15,15};
  private static int[] westboundStopSelect = {16,16,17,18,19,20,21,22,23,24,25,26,27,28,29,29,30,30}; // 30 represents stop 0

  public Rider(int stopOfBoarding){
    arrivalTime = BusSim.agenda.getCurrentTime();
    boardingStop = stopOfBoarding;
    destinationStop = boardingStop;
    Random destinationGenerator = new Random();
    int randomIndex = 0;
    // randomly generate destinationStop
    while (destinationStop <= boardingStop){
      randomIndex = destinationGenerator.nextInt(18);
      if (boardingStop < 15){ // eastbound boardingStop
        destinationStop = eastboundStopSelect[randomIndex];
      } // if
      else { // westbound boardingStop
        destinationStop = westboundStopSelect[randomIndex];
      } // else
    } // while
    if (destinationStop == 30){
      destinationStop = 0;
    } // if
  } // Rider

  public int getBoardingStop(){
    return boardingStop;
  } // getBoardingStop

  public int getDestinationStop(){
    return destinationStop;
  } // getDestinationStop

  public double getArrivalTime(){
    return arrivalTime;
  } // getArrivalTime

} // Rider
