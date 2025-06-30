import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class FindingArticulationPoints {
    private final int V;
    private final LinkedList<Integer> adj[];
    int time = 0;

    // Constructor remains same as before
    // ...existing code...
    @SuppressWarnings("unchecked")
    FindingArticulationPoints(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v's list.
        adj[w].add(v); // Add v to w's list
    }

    void AP() {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];
        boolean ap[] = new boolean[V]; // Store articulation points

        Arrays.fill(parent, -1);
        Arrays.fill(visited, false);
        Arrays.fill(ap, false);

        // Call the recursive helper function for each undiscovered vertex
        for (int i = 0; i < V; i++)
            if (!visited[i])
                APUtil(i, visited, disc, low, parent, ap);

        // Print all articulation points
        for (int i = 0; i < V; i++)
            if (ap[i])
                System.out.print(i + " ");
    }

    void APUtil(int u, boolean visited[], int disc[], int low[],
            int parent[], boolean ap[]) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (Integer v : adj[u]) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                APUtil(v, visited, disc, low, parent, ap);

                low[u] = Math.min(low[u], low[v]);

                // Case 1: u is root and has two or more children
                if (parent[u] == -1 && children > 1)
                    ap[u] = true;

                // Case 2: u is not root and low value of one of its children
                // is more than discovery value of u
                if (parent[u] != -1 && low[v] >= disc[u])
                    ap[u] = true;
            } else if (v != parent[u])
                low[u] = Math.min(low[u], disc[v]);
        }
    }

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            int v = sc.nextInt();
            int e = sc.nextInt();

            FindingArticulationPoints g = new FindingArticulationPoints(v);
            for (int i = 0; i < e; i++) {
                int end1 = sc.nextInt();
                int end2 = sc.nextInt();
                g.addEdge(end1, end2);
            }
            g.AP();
            sc.close();
        }
    }
}