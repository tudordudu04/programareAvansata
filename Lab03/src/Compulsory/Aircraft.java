package Compulsory;

public class Aircraft implements Comparable<Aircraft>{
    String name;
    String model;
    int tailNumber;

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getTailNumber(){
        return tailNumber;
    }

    @Override
    public int compareTo(Aircraft other) {
        if(this.name != null)
            return this.name.compareTo(other.name);
        return -1;
    }
}
