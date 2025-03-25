package Homework;
import Compulsory.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.graph4j.*;
import com.github.javafaker.*;
import org.graph4j.shortestpath.DijkstraShortestPathDefault;

import static org.graph4j.GraphTests.isConnected;

public class Homework {
    public static void main(String[] args) {
        Graph<Integer, Path> graph = GraphBuilder.empty().buildGraph();

        List<Location> locations = IntStream.rangeClosed(0, 15)
                .mapToObj(i ->{
                    int pick = new Random().nextInt(Type.values().length);
                    Type rand = Type.values()[pick];
                    Location current = new Location(Faker.instance().address().city(), rand);
                    graph.addVertex(i);
                    return current;}
                )
                .collect(Collectors.toCollection(ArrayList::new));
//        System.out.println(locations);

        Random random = new Random();
        while(true) {
            for (int i = 0; i < locations.size(); i++) {
                for (int j = i + 1; j < locations.size(); j++) {
                    boolean canMoveDirectly = random.nextBoolean();
                    if (canMoveDirectly) {
                        double time = -1, probability = -1;
                        switch (locations.get(j).getType()) {
                            case ENEMY -> {
                                time = random.nextDouble(15);
                                probability = random.nextDouble(25);
                                break;
                            }
                            case NEUTRAL -> {
                                time = random.nextDouble(30);
                                probability = random.nextDouble(30) + 40;
                                break;
                            }
                            case FRIENDLY -> {
                                time = random.nextDouble(60);
                                probability = random.nextDouble(50) + 50;
                                break;
                            }
                        }
                        Path path = new Path(time, probability);
                        graph.addLabeledEdge(locations.indexOf(locations.get(i)), locations.indexOf(locations.get(j)), path, time);
                    }
                }
            }
            if(isConnected(graph))
                break;
        }

        Set<Location> friendlyLocations = locations.stream()
                .filter(loc -> loc.getType() == Type.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));
//        System.out.println("Friendly locations: " + friendlyLocations);

        List<Location> enemyLocations = locations.stream()
                .filter(loc -> loc.getType() == Type.ENEMY)
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));
//        System.out.println("Enemy locations: " + enemyLocations);

        Queue<Location> neutralLocations = locations.stream()
                .filter(loc -> loc.getType() == Type.NEUTRAL)
                .collect(Collectors.toCollection(PriorityQueue::new));
//        System.out.println("Neutral locations: " + neutralLocations);

        Location startLocation = locations.getFirst();
        var dijkstra = new DijkstraShortestPathDefault(graph, 0);
        Map<Location, Double> shortestTimeRelativeToStart = locations.stream()
                .filter(loc -> !loc.equals(startLocation))
                .collect(Collectors.toMap(loc -> loc, loc -> dijkstra.getPathWeight(locations.indexOf(loc))));

        System.out.println("Friendly locations:");
        printPathType(shortestTimeRelativeToStart, Type.FRIENDLY);
        System.out.println("Enemy locations:");
        printPathType(shortestTimeRelativeToStart, Type.ENEMY);
        System.out.println("Neutral locations:");
        printPathType(shortestTimeRelativeToStart, Type.NEUTRAL);

    }

    public static void printPathType(Map<Location, Double> map, Type type){
        for(Location aux : map.keySet())
            if(aux.getType() == type)
                System.out.println("Location: " + aux + "and time to get there: " + map.get(aux) + ".");
    }
}