import java.util.*;

public class lab6 {

    private Map<Integer, List<Integer>> adjList = new HashMap<>();
    private boolean isDirected;

    public lab6(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.get(u).add(v);

        if (!isDirected) {
            adjList.putIfAbsent(v, new ArrayList<>());
            adjList.get(v).add(u);
        }
    }

    public void bfsTraversal(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        System.out.println("\nBFS Traversal:");

        if (!adjList.containsKey(startNode)) {
            System.out.println("Starting node " + startNode + " does not exist in the graph.");
            return;
        }

        for (int node : adjList.keySet()) {
            if (!visited.contains(node)) {
                // If disconnected and startNode not yet visited, start from it first
                if (node == startNode || visited.isEmpty()) {
                    bfsComponent(node, visited, queue);
                }
            }
        }

        // Handle any disconnected components that were not reachable from startNode
        for (int node : adjList.keySet()) {
            if (!visited.contains(node)) {
                System.out.println("Disconnected Component:");
                bfsComponent(node, visited, queue);
            }
        }

        System.out.println("\nTotal nodes visited: " + visited.size());
    }

    private void bfsComponent(int startNode, Set<Integer> visited, Queue<Integer> queue) {
        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Is the graph directed? (yes/no): ");
        boolean isDirected = scanner.next().equalsIgnoreCase("yes");

        lab6 graph = new lab6(isDirected);

        System.out.print("Enter the number of edges: ");
        int edgeCount = scanner.nextInt();

        System.out.println("Enter each edge (format: u v):");
        for (int i = 0; i < edgeCount; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        System.out.print("Enter the starting node for BFS: ");
        int startNode = scanner.nextInt();

        graph.bfsTraversal(startNode);
    }
}
