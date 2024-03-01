import java.util.*;

public class Graph {
    Map<String, List<Edge>> adjacencyList;

    public Graph(){
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(String start, String end, float distance){
        adjacencyList.putIfAbsent(start, new ArrayList<>());
        adjacencyList.get(start).add(new Edge(start, end, distance));

        adjacencyList.putIfAbsent(end, new ArrayList<>());
        adjacencyList.get(end).add(new Edge(end,start,distance));
    }

    public List<Edge> findMinimumSpanningTree(String startCity){
        Set<String> visited = new HashSet<>();
        List<Edge> result = new ArrayList<>();

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        minHeap.addAll(adjacencyList.getOrDefault(startCity, Collections.emptyList()));

        visited.add(startCity);

        while(!minHeap.isEmpty()){
            Edge currentEdge = minHeap.poll();

            if(!visited.contains(currentEdge.end)){
                visited.add(currentEdge.end);
                result.add(currentEdge);
                minHeap.addAll(adjacencyList.getOrDefault(currentEdge.end, Collections.emptyList()));
            }
        }
        return result;
    }
}
