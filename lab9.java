import java.util.*;

class lab9 {

    static class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Pair implements Comparable<Pair> {
        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int compareTo(Pair other) {
            return this.weight - other.weight;
        }
    }

    static void primMST(List<List<Edge>> graph, int startVertex) {
        int V = graph.size();
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];       // Minimum weight to connect
        int[] parent = new int[V];    // Store MST structure

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        key[startVertex] = 0;
        pq.offer(new Pair(startVertex, 0));

        System.out.println("Order of adding nodes to MST and edge weights:");

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.vertex;

            if (inMST[u]) continue;

            inMST[u] = true;

            // If u is not the starting vertex, print the edge added
            if (parent[u] != -1) {
                System.out.println("Added node: " + u + " via edge (" + parent[u] + " - " + u + ") with weight " + key[u]);
            } else {
                System.out.println("Added starting node: " + u);
            }

            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.offer(new Pair(v, key[v]));
                }
            }
        }

        System.out.println("\nFinal MST edges:");
        int totalWeight = 0;
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + " : " + key[i]);
                totalWeight += key[i];
            }
        }

        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add undirected edges
        graph.get(0).add(new Edge(1, 2));
        graph.get(1).add(new Edge(0, 2));

        graph.get(0).add(new Edge(3, 6));
        graph.get(3).add(new Edge(0, 6));

        graph.get(1).add(new Edge(2, 3));
        graph.get(2).add(new Edge(1, 3));

        graph.get(1).add(new Edge(3, 8));
        graph.get(3).add(new Edge(1, 8));

        graph.get(1).add(new Edge(4, 5));
        graph.get(4).add(new Edge(1, 5));

        graph.get(2).add(new Edge(4, 7));
        graph.get(4).add(new Edge(2, 7));

        // Start Prim’s algorithm from node 0
        primMST(graph, 0);
    }
}
