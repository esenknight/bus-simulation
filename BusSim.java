// primary file for simulation of bus route

import java.io.PrintWriter;
import java.io.File;

public class BusSim{

  public static PQ agenda = new PQ();
  public static Statistics stats = new Statistics();
  private static Stop[] stopList = new Stop[30];

  // variables to be initialized in main
  private static Bus[] regularBusList;
  private static Bus[] expressBusList;
  private static int arrivalFrequency;
  private static int busQuantity;
  private static int expressBusQuantity;

  public static Stop getStop(int index){
    return stopList[index];
  } // getStop

  public static void main(String[] args){
    // creating file to which statistics will be recorded after simulation has completed
    // file name is number of regular buses + number of express buses + simulation run time + frequency interval of rider arivals
    PrintWriter printWriter;
      try {
        printWriter = new PrintWriter(new File (10 + "Reg2Exp10000Time60Load" + ".txt"));
      } catch (Exception e) {
        System.out.println("an error has occured");
        return;
      } // catch

      //user-adjustable system parameters
      int arrivalFrequency = 60;
      int busQuantity = 10;
      int expressBusQuantity = 2; // when changing parameter, additional changes needed at initialization of buses

      // making the stops, initializing the waitlines
      for (int i = 0; i < 30; i++){
        stopList[i] = new Stop();
      } // for

      // initializing and placing Regular Buses
      regularBusList = new Bus[busQuantity];
      for (int h = 0; h < busQuantity; h++){
        regularBusList[h] = new Bus();
        agenda.add(new BusEvent((h*(30/busQuantity)),regularBusList[h],0),0);
      } // for

      // initializing and placing Express Buses
      // when changing number of express buses, new initialization statements and different placement locations needed
      expressBusList = new Bus[expressBusQuantity];
      expressBusList[0] = new Bus();
      expressBusList[1] = new Bus();
      // expressBusList[2] = new Bus();
      agenda.add(new ExpressBusEvent(0,expressBusList[0],0),0);
      // agenda.add(new ExpressBusEvent(10,expressBusList[1],0),0); // use when 3 Express Buses
      agenda.add(new ExpressBusEvent(15,expressBusList[1],0),0); // use when 2 Express Buses
      // agenda.add(new ExpressBusEvent(20,expressBusList[2],0),0);

      // adding RiderEvents for the busy stops to the agenda, 6 total
      agenda.add(new RiderEvent(0,arrivalFrequency/2),0);
      agenda.add(new RiderEvent(1,arrivalFrequency/2),0);
      agenda.add(new RiderEvent(14,arrivalFrequency/2),0);
      agenda.add(new RiderEvent(15,arrivalFrequency/2),0);
      agenda.add(new RiderEvent(16,arrivalFrequency/2),0);
      agenda.add(new RiderEvent(29,arrivalFrequency/2),0);

      // adding RiderEvents for the remaining eastbound stops, 12 total
      for (int j = 2; j < 14; j++){
        agenda.add(new RiderEvent(j,arrivalFrequency),0);
      } // for

      // adding RiderEvents for the remaining westbound stops, 12 total
      for (int k = 17; k < 29; k++){
        agenda.add(new RiderEvent(k,arrivalFrequency),0);
      } // for

      // running simulation
      while (agenda.getCurrentTime() <= 10000){
        agenda.remove().run();
      } // while

      // final stats, printing to terminal
      stats.updateOverallStats();
      System.out.println(stats);
      // writing to file
      printWriter.println(stats);
      printWriter.close();
  } // main

} // BusSim
