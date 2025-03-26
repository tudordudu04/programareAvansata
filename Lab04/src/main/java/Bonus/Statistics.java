package Bonus;

public class Statistics {
    static int nrLocations = 0;
    static int maxNrNeighbours = 0;
    static double safestPath = 0;

    public void setNrLocations(int nrLocations) {
        Statistics.nrLocations = nrLocations;
    }
    public void setMaxNrNeighbours(int maxNrNeighbours) {
        Statistics.maxNrNeighbours = maxNrNeighbours;
    }
    public void setSafestPath(double safestPath) {
        Statistics.safestPath = safestPath;
    }
    public int getNrLocations() {
        return nrLocations;
    }
    public int getMaxNrNeighbours() {
        return maxNrNeighbours;
    }
    public double getSafestPath() {
        return safestPath;
    }
    public Statistics getStatistics() {
        return this;
    }

    @Override
    public String toString() {
        return ("Nr. loc.: " + nrLocations + " max nr. neighbours: " + maxNrNeighbours + " safest path: " + safestPath + ".");
    }
}
