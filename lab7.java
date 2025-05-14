import java.util.*;

public class lab7 {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter number of edges: ");
        int edges = scanner.nextInt();

        System.out.print("Is the graph directed? (yes/no): ");
        boolean isDirected = scanner.next().equalsIgnoreCase("yes");

        // Initialize graph
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Read edges
        System.out.println("Enter the edges (format: from to):");
        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            graph.get(from).add(to);
            if (!isDirected) {
                graph.get(to).add(from);
            }
        }

        System.out.print("Enter starting node for DFS: ");
        int startNode = scanner.nextInt();

        // DFS traversal
        System.out.println("\nDFS Traversal:");
        if (!visited.contains(startNode)) {
            dfs(startNode);
            System.out.println();
        }

        // Check and traverse disconnected components
        for (int node = 0; node < vertices; node++) {
            if (!visited.contains(node)) {
                System.out.println("Disconnected component starting at node " + node + ":");
                dfs(node);
                System.out.println();
            }
        }

        System.out.println("Total nodes visited: " + visited.size());
    }

    private static void dfs(int node) {
        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }
}
