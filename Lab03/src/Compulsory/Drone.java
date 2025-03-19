package Compulsory;

public class Drone extends Aircraft implements CargoCapable{
    int batteryLife;
    int maximumPayload;
    int actualPayload = 0;

    public Drone(String name, String model, int tailNumber, int batteryLife, int maximumPayload) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
        this.batteryLife = batteryLife;
        this.maximumPayload = maximumPayload;
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

    public int getBatteryLife() {
        return batteryLife;
    }


}
