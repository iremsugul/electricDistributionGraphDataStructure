public class Edge implements Comparable<Edge>{
    String start;
    String end;
    float distance;

    public Edge(String start, String end, float distance){
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.distance, o.distance);
    }
}
