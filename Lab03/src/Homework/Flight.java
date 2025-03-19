package Homework;
import Compulsory.*;

public class Flight {
    Runway assignedDestination = null;
    Interval interval;
    Aircraft assignedAircraft;
    String aircraftId;

    public Flight(Interval interval, Aircraft assignedAircraft, String aircraftId) {
        this.interval = new Interval(interval.startTime, interval.endTime);
        this.assignedAircraft = assignedAircraft;
        this.aircraftId = aircraftId;
    }

    public void setAssignedDestination(Runway assignedDestination) {
        this.assignedDestination = assignedDestination;
    }
    public Runway getAssignedDestination() {
        return assignedDestination;
    }
    public Interval getInterval() {
        return interval;
    }
    public Aircraft getAssignedAircraft() {
        return assignedAircraft;
    }
    public String getAircraftId() {
        return aircraftId;
    }

    public void printFlight(){
        System.out.println("Runway: " + assignedDestination.getLocation() + ", Interval: " + interval.intervalToString() +
                ", Aircraft: " + assignedAircraft.getName() + ", Aircraft ID: " + aircraftId);
    }

}
