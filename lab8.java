import java.util.*;

public class lab8 {
    static final int INF = Integer.MAX_VALUE;

    // Function to find the vertex with the minimum distance value
    static int minDistance(int[] dist, boolean[] visited) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Dijkstra's algorithm implementation
    static void dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V]; // The output array. dist[i] will hold the shortest distance from src to i
        boolean[] visited = new boolean[V]; // visited[i] will be true if vertex i is included in shortest path tree

        // Initialize all distances as INFINITE and visited[] as false
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);

        dist[src] = 0; // Distance of source vertex from itself is always 0

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the shortest distance array
        System.out.println("\nVertex \t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + (dist[i] == INF ? "∞" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input graph size
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        // Input graph as adjacency matrix
        System.out.println("Enter the adjacency matrix (enter 0 if no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // Input source node
        System.out.print("Enter source vertex (0 to " + (V - 1) + "): ");
        int src = sc.nextInt();

        // Run Dijkstra’s Algorithm
        dijkstra(graph, src);
    }
}
