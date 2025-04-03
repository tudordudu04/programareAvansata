package Bonus;

import Compulsory.*;
import Homework.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class EquitableAirport {
    ArrayList<Runway> runwaysList = new ArrayList<>();
    HashSet<Flight> flightList = new HashSet<>();
    Map<Runway, HashSet<Flight>> flightAssignments = new HashMap<>();

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

    boolean assignFlight(Flight flight) {
        int ok = 0;
        for (int i = 0; i < runwaysList.size(); i++) {
            Runway runwayAux = runwaysList.get(i);
            if (!flightAssignments.containsKey(runwayAux)) {
                HashSet<Flight> flightSet = new HashSet<>();
                flightSet.add(flight);
                flightAssignments.put(runwayAux, flightSet);
                flight.setAssignedDestination(runwayAux);
                ok = 1;
                break;
            } else if(flightAssignments.get(runwayAux).size() < flightList.size() / runwaysList.size()+1){
                int ok1 = 1;
                for (Flight aux : flightAssignments.get(runwayAux)) {
                    if (aux.getInterval().overlaps(flight.getInterval())) {
                        ok1 = 0;
                        break;
                    }
                }
                if (ok1 == 1) {
                    flightAssignments.get(runwayAux).add(flight);
                    flight.setAssignedDestination(runwayAux);
                    ok = 1;
                    break;
                }
                else{
                    flight.getInterval().reschedule(flight.getInterval().getStartTime(), flight.getInterval().getEndTime().plusHours(2));
                    i--;
                }
            }
        }
        return ok == 1;
    }

    public void printFlights(){
        for(Flight flight : flightList) {
            flight.printFlight();
        }
    }
}
