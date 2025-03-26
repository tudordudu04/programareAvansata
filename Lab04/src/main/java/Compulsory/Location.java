package Compulsory;

public class Location implements Comparable<Location> {
    String name;
    Type type;

    public Location(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Location other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return ("Nume: " + name + ", Tip: " + type + " ");
    }



}
