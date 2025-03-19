package Compulsory;

public class Airliner extends Aircraft implements PassengerCapable{
    int maxNrOfPassengers;
    int wingSpan;
    int currentPassengerCount = 0;

    public Airliner(String name, String model, int tailNumber, int maxNrOfPassengers, int wingSpan) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
        this.maxNrOfPassengers = maxNrOfPassengers;
        this.wingSpan = wingSpan;
    }

    public boolean areSeatsAvailable() {
        return (currentPassengerCount == maxNrOfPassengers);
    }

    public void unloadPlane() {
        currentPassengerCount = 0;
    }

    public int getSeatsAvailable() {
        return maxNrOfPassengers - currentPassengerCount;
    }
}
