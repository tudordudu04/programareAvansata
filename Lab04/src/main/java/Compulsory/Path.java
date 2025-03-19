package Compulsory;

public class Path {
    Location from;
    Location to;
    int time;
    int safetyProbability;

    public Path(Location from, Location to, int time, int safetyProbability) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.safetyProbability = safetyProbability;
    }

    public Location getFrom() {
        return from;
    }
    public Location getTo() {
        return to;
    }
    public double getTime() {
        return time;
    }
    public double getSafetyProbability() {
        return safetyProbability;
    }

}
