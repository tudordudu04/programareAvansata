package Compulsory;

public class Path{
    double time;
    double safetyProbability;

    public Path(double time, double safetyProbability) {
        this.time = time;
        this.safetyProbability = safetyProbability;
    }

    public double getTime() {
        return time;
    }
    public double getSafetyProbability() {
        return safetyProbability;
    }
    public Path getPath(){
        return this;
    }

    @Override
    public String toString() {
        return ("Time: " + time + ", Safety Prob: " + safetyProbability + " ");
    }

}
