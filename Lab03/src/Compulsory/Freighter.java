package Compulsory;

public class Freighter extends Aircraft implements PassengerCapable, CargoCapable {
    int wingSpan;
    int maximumPayload;
    int actualPayload = 0;
    int maxNrOfPassengers;
    int currentPassengerCount = 0;

    public Freighter(String name, String model, int tailNumber, int maximumPayload, int wingSpan) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
        this.maximumPayload = maximumPayload;
        this.wingSpan = wingSpan;
    }

    public boolean isFull() {
        return (actualPayload == maximumPayload);
    }

    public void addCargo(int weight) {
        actualPayload += weight;
    }

    public int getCargo() {
        int aux = actualPayload;
        actualPayload = 0;
        return aux;
    }

    public int getMaximumPayload() {
        return maximumPayload;
    }

    public int getWingSpan() {
        return wingSpan;
    }

    public boolean areSeatsAvailable() {
        return (currentPassengerCount != maxNrOfPassengers);
    }

    public int getSeatsAvailable() {
        return currentPassengerCount;
    }

    public void unloadPlane() {
        currentPassengerCount = 0;
    }


}
