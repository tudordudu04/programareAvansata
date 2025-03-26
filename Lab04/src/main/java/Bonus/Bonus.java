package Bonus;
import Compulsory.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.graph4j.*;
import com.github.javafaker.*;
import org.graph4j.shortestpath.DijkstraShortestPathDefault;

import static org.graph4j.GraphTests.isConnected;

public class Bonus {
    public static void main(String[] args) {
        Graph<Integer, Path> graph = GraphBuilder.empty().buildGraph();
        Random random = new Random();

        List<Location> locations = IntStream.rangeClosed(0, 200)
                .mapToObj(i ->{
                    int pick = new Random().nextInt(Type.values().length);
                    Type rand = Type.values()[pick];
                    Location current = new Location(Faker.instance().address().city(), rand);
                    graph.addVertex(i);
                    return current;}
                )
                .collect(Collectors.toCollection(ArrayList::new));
//        System.out.println(locations);

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
                        graph.addLabeledEdge(locations.indexOf(locations.get(i)), locations.indexOf(locations.get(j)), path, probability);
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
        Map<Location, Map<Location, Map<Type, Double>>> pairOfLocations = new HashMap<>();
        for(int i = 0; i< locations.size(); i++) {
            var startLocation = new DijkstraShortestPathDefault(graph, i);
            Location start = locations.get(i);
            Map<Location, Map<Type, Double>> safestPath = locations.stream()
                    .filter(loc -> !loc.equals(start))
                    .collect(Collectors.toMap(loc -> loc, loc -> {
                        Map<Type, Double> map = new HashMap<>();
                        var path = startLocation.computePath(locations.indexOf(loc));
                        for (var vertex : path) {
                            Location location = locations.get(vertex);
                            map.put(location.getType(), map.getOrDefault(location.getType(), 0.0) + 1);
                        }
                        return map;
                    }));
            pairOfLocations.put(start, safestPath);
        }

        for(Location location : locations) {
            System.out.println("Locatia: " + location);
            System.out.println("Vecini:");
            for(Location aux : pairOfLocations.get(location).keySet())
            {
                System.out.println("Node: " + locations.indexOf(aux) + " " + aux);
                pairOfLocations.get(location).get(aux).forEach((type, Double) -> System.out.println("Type: " + type + " and count: " + Double));
                System.out.println();
            }
            System.out.println("\n");
        }

        Statistics.nrLocations = pairOfLocations.size();
        Statistics.maxNrNeighbours = pairOfLocations.values().stream()
                .mapToInt(Map::size)
                .max()
                .getAsInt();
        Statistics.safestPath = pairOfLocations.values().stream()
                .flatMap(in -> in.values().stream())
                .flatMap(in -> in.values().stream())
                .mapToDouble(Double::doubleValue)
                .min()
                .getAsDouble();
    }

}