
import java.util.*;

public class Connected_Components_DFS {
    static int[][] grid; // Adjacency matrix to represent the graph
    static boolean[] visited; // Array to track visited nodes

    // DFS method to traverse the graph
    static void dfs(int node, int n) {
        visited[node] = true;
        for (int neighbor = 0; neighbor < n; neighbor++) {
            if (grid[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, n);
            }
        }
    }

    public static void main(String[] args) {
        // Read number of cities (N) and routes (M)
        try (Scanner sc = new Scanner(System.in)) {
            // Read number of cities (N) and routes (M)
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            // Initialize adjacency matrix and visited array
            grid = new int[n][n];
            visited = new boolean[n];
            
            // Read the edges and populate the adjacency matrix
            for (int i = 0; i < m; i++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                grid[v1][v2] = 1;
                grid[v2][v1] = 1;
            }
            
            // Count the number of connected components
            int regions = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(i, n);
                    regions++;
                }
            }
            
            // Print the number of regions
            System.out.println(regions);
        }
    }
}