import java.util.Scanner;

class lab10 {
    
    // A class to represent a directed edge with a source, destination, and weight
    static class Edge {
        int source, destination, weight;
        
        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    // Bellman-Ford algorithm to find shortest paths
    public static void bellmanFord(Edge[] edges, int vertices, int source) {
        int[] distance = new int[vertices];
        
        // Initialize distances from source to all other vertices as INFINITE
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        
        // Relax edges repeatedly
        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[edge.source] + edge.weight;
                }
            }
        }
        
        // Check for negative weight cycles
        for (Edge edge : edges) {
            if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }
        
        // Print the shortest distance to all vertices
        System.out.println("Vertex Distance from Source:");
        for (int i = 0; i < vertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + " is unreachable");
            } else {
                System.out.println("Vertex " + i + " : " + distance[i]);
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input number of vertices and edges
        System.out.print("Enter number of vertices: ");
        int vertices = scanner.nextInt();
        System.out.print("Enter number of edges: ");
        int edgesCount = scanner.nextInt();
        
        Edge[] edges = new Edge[edgesCount];
        
        // Input edges
        System.out.println("Enter the edges (source destination weight): ");
        for (int i = 0; i < edgesCount; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            edges[i] = new Edge(source, destination, weight);
        }
        
        // Input source vertex
        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();
        
        // Run the Bellman-Ford algorithm
        bellmanFord(edges, vertices, source);
        
        scanner.close();
    }
}
