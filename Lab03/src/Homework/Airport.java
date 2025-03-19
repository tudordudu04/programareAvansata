package Homework;
import Compulsory.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Airport {
    ArrayList<Runway> runwaysList = new ArrayList<>();
    HashSet<Flight> flightList = new HashSet<>();
    HashMap<Runway, HashSet<Flight>> flightAssignments = new HashMap<>();

    public void addRunway(Runway runway) {
        runwaysList.add(runway);
    }

    public boolean addFlight(Flight flight) {
        if (assignFlight(flight)) {
            flightList.add(flight);
            return true;
        }
        return false;
    }

    public boolean assignFlight(Flight flight) {
        int ok = 0;
        for (Runway runwayAux : runwaysList) {
            if (!flightAssignments.containsKey(runwayAux)) {
                HashSet<Flight> flightSet = new HashSet<>();
                flightSet.add(flight);
                flightAssignments.put(runwayAux, flightSet);
                flight.setAssignedDestination(runwayAux);
                ok = 1;
                break;
            } else {
                int ok1 = 1;
                for (Flight aux : flightAssignments.get(runwayAux)) {
                    if (aux.interval.overlaps(flight.interval)) {
                        ok1 = 0;
//                        System.out.println("Overlaps");
//                        System.out.println(aux.interval.intervalToString());
//                        System.out.println(flight.interval.intervalToString());
                        break;
                    }
                }
                if (ok1 == 1) {
                    flightAssignments.get(runwayAux).add(flight);
                    flight.setAssignedDestination(runwayAux);
                    ok = 1;
                    break;
                }
            }
        }
        if(ok == 1) {
            return true;
        }
        return false;
    }

    public void printFlights(){
//        System.out.println(flightAssignments);
        for(Flight flight : flightList) {
            flight.printFlight();
        }
    }

}
