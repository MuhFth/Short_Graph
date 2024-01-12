import java.util.*;

class Graph {
    private int V;
    private Map<Integer, List<Node>> adjNodes;

    public Graph(int V) {
        this.V = V;
        adjNodes = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adjNodes.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Node node = new Node(destination, weight);
        adjNodes.get(source).add(node);
    }

    public void dijkstra(int startVertex) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);

        priorityQueue.add(new Node(startVertex, 0));
        distance[startVertex] = 0;

        while (!priorityQueue.isEmpty()) {
            int currentVertex = priorityQueue.poll().vertex;

            for (Node neighbor : adjNodes.get(currentVertex)) {
                int newDistance = distance[currentVertex] + neighbor.weight;

                if (newDistance < distance[neighbor.vertex]) {
                    distance[neighbor.vertex] = newDistance;
                    priorityQueue.add(new Node(neighbor.vertex, newDistance));
                }
            }
        }

        // Print the shortest distances
        System.out.println("Shortest distances from start vertex " + startVertex + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To vertex " + i + ": " + distance[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 4, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 0, 1);
        graph.addEdge(3, 2, 5);
        graph.addEdge(4, 3, 4);

        int startVertex = 0;
        graph.dijkstra(startVertex);
    }
}

class Node implements Comparable<Node> {
    public int vertex;
    public int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.weight, other.weight);
    }
}
