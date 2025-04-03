package Compulsory;
import Bonus.EquitableAirport;
import Homework.*;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        Airliner airplane1 = new Airliner("Delta", "747", 1, 524, 67);
        Airliner airplane2 = new Airliner("Boeing", "747", 2, 480, 60);
        Airliner airplane3 = new Airliner("Aro", "100", 3, 230, 38);
        Freighter cargoShip1 = new Freighter("Airbus", "A300", 1, 37000, 48);
        Freighter cargoShip2 = new Freighter("Airbus", "A320", 2, 40000, 52);
        Freighter cargoShip3 = new Freighter("Airbus", "A330", 3, 42000, 54);
        Drone drone1 = new Drone("DJI Mavic", "3", 1, 30, 25);
        Drone drone2 = new Drone("Autel", "Mini", 2, 15, 10);
        Drone drone3 = new Drone("Autel", "Max", 3, 20, 15);

        Aircraft[] airplanes  = new Aircraft[6];
            airplanes[0] = cargoShip1;
            airplanes[1] = cargoShip2;
            airplanes[2] = drone1;
            airplanes[3] = drone2;
            airplanes[4] = airplane1;
            airplanes[5] = airplane2;

        //Prints the cargo capable objects
        for(int i = 0; i < airplanes.length; i++) {
        //if(airplanes[i] instanceof Drone) {}
            if(airplanes[i].getClass().equals(Freighter.class)) {
                    Freighter freighter = (Freighter) airplanes[i];
                    System.out.println(freighter.getName() + " " + freighter.getModel() + " " + freighter.getTailNumber() + freighter.getMaximumPayload() + " " + freighter.getWingSpan());
            }
            else if (airplanes[i].getClass().equals(Drone.class)) {
                Drone drone = (Drone) airplanes[i];
                System.out.println(drone.getName() + " " + drone.getModel() + " " + drone.getTailNumber() + drone.getMaximumPayload() + " " + drone.getBatteryLife());
            }
        }

        //Homework

        EquitableAirport hCoanda = new EquitableAirport();
        //Runways
            hCoanda.addRunway(new Runway("West Wing"));
            hCoanda.addRunway(new Runway("East Wing"));
            hCoanda.addRunway(new Runway("North Wing"));
            hCoanda.addRunway(new Runway("South Wing"));

        //Intervals
            Interval firstInterval = new Interval(LocalTime.of(0, 0), LocalTime.of(2, 0));
            Interval secondInterval = new Interval(LocalTime.of(1, 30), LocalTime.of(3, 0));
            Interval thirdInterval = new Interval(LocalTime.of(3, 1), LocalTime.of(4, 0));
            Interval fourthInterval = new Interval(LocalTime.of(5, 0), LocalTime.of(7, 0));

        //Flights
            if(!hCoanda.addFlight(new Flight (firstInterval, airplane1, "01")))
                System.out.println("Flight: " + airplane1.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (secondInterval, airplane2, "02")))
                System.out.println("Flight: " + airplane2.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (firstInterval, airplane3, "03")))
                System.out.println("Flight: " + airplane3.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (firstInterval, cargoShip1, "04")))
                System.out.println("Flight: " + cargoShip1.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (thirdInterval, cargoShip2, "05")))
                System.out.println("Flight: " + cargoShip2.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (thirdInterval, cargoShip3, "06")))
                System.out.println("Flight: " + cargoShip3.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (firstInterval, drone1, "07")))
                System.out.println("Flight: " + drone1.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (firstInterval, drone2, "08")))
                System.out.println("Flight: " + drone2.name + " couldn't be added because of scheduling issues.");
            if(!hCoanda.addFlight(new Flight (fourthInterval, drone3, "09")))
                System.out.println("Flight: " + drone3.name + " couldn't be added because of scheduling issues.");

        hCoanda.printFlights();

    }
}