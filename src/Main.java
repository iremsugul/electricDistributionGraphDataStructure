import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<City> cities = new ArrayList<>();
        Graph graph = new Graph();
        String startCity;

        try {
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();
            startCity = null;

            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                cities.add(new City(name, x,y));

                if (startCity == null || name.compareTo(startCity) < 0) {
                    startCity = name;
                }
            }

            for(int i =0; i<cities.size(); i++){
                for(int j =i+1; j<cities.size(); j++){
                    graph.addEdge(cities.get(i).name, cities.get(j).name, calculateDistance(cities.get(i).x, cities.get(i).y, cities.get(j).x, cities.get(j).y));
                    graph.addEdge(cities.get(j).name, cities.get(i).name, calculateDistance(cities.get(j).x, cities.get(j).y, cities.get(i).x, cities.get(i).y));
                }
            }

            List<Edge> minimumSpanningTree = graph.findMinimumSpanningTree(startCity);
            minimumSpanningTree.sort(Edge::compareTo);
            System.out.println("Paths are:");
            for (Edge edge : minimumSpanningTree) {
                System.out.println(edge.start + "-" + edge.end + ": " + String.format(Locale.US,"%.1f", edge.distance));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float calculateDistance(int x1, int y1, int x2, int y2){
        double distance =  Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2, 2));
        return (float) distance;
    }
}