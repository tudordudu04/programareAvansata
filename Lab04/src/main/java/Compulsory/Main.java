package Compulsory;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Location> locations = List.of(new Location("Iasi", Type.FRIENDLY),
                new Location("Bucuresti", Type.ENEMY),
                new Location("Suceava", Type.NEUTRAL),
                new Location("Bacau", Type.FRIENDLY),
                new Location("Vaslui", Type.ENEMY),
                new Location("Maramures", Type.FRIENDLY),
                new Location("Botosani", Type.NEUTRAL));


        TreeSet<Location> friendlyLocations = locations.stream()
                .filter(loc -> loc.getType() == Type.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly locations: " + friendlyLocations);


        LinkedList<Location> enemyLocations = locations.stream()
                .filter(loc -> loc.getType() == Type.ENEMY)
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("Enemy locations: " + enemyLocations);
    }
}