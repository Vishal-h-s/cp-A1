
import java.util.*;

public class FindingBridges {
    private final int V; // No. of vertices
    // Array of lists for Adjacency List Representation
    private final LinkedList<Integer> adj[];
    int time = 0;

    // Constructor
    @SuppressWarnings("unchecked")
    FindingBridges(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v's list.
        adj[w].add(v); // Add v to w's list
    }

    // DFS based function to find all bridges
    void bridge() {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];

        // Initialize parent and visited arrays
        Arrays.fill(parent, -1);
        Arrays.fill(visited, false);

        // Call the recursive helper function to find bridges
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bridgeUtil(i, visited, disc, low, parent);
            }
        }
    }

    // A recursive function that finds and prints bridges using DFS traversal
    void bridgeUtil(int u, boolean visited[], int disc[], int low[], int parent[]) {
        // Mark the current node as visited
        visited[u] = true;

        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;

        // Iterate through all vertices adjacent to this vertex
        for (int v : adj[u]) {
            // If v is not visited, make it a child of u in DFS tree and recur
            if (!visited[v]) {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);

                // Check if the subtree rooted at v has a connection back to one of the
                // ancestors of u
                low[u] = Math.min(low[u], low[v]);

                // If the lowest vertex reachable from subtree under v is below u in DFS tree,
                // then u-v is a bridge
                if (low[v] > disc[u]) {
                    System.out.println(u + " " + v);
                }
            }
            // Update low value of u for parent function calls
            else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in) // Close the scanner to prevent resource leaks
        ) {
            int v = sc.nextInt();
            int e = sc.nextInt();

            FindingBridges g = new FindingBridges(v);
            for (int i = 0; i < e; i++) {
                int end1 = sc.nextInt();
                int end2 = sc.nextInt();
                g.addEdge(end1, end2);
            }
            g.bridge();
            sc.close();
        }
    }
}