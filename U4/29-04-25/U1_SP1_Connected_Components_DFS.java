/*
 * There are N cities, and M routes[], each route is a path between two cities.
 * routes[i] = [city1, city2], there is a travel route between city1 and city2.
 * Each city is numbered from 0 to N-1.
 * 
 * There are one or more Regions formed among N cities.
 * A Region is formed in such way that you can travel between any two cities
 * in the region that are connected directly and indirectly.
 * 
 * Your task is to findout the number of regions formed between N cities.
 * 
 * Input Format:
 * -------------
 * Line-1: Two space separated integers N and M, number of cities and routes
 * Next M lines: Two space separated integers city1, city2.
 * 
 * Output Format:
 * --------------
 * Print an integer, number of regions formed.
 * 
 * 
 * Sample Input-1:
 * ---------------
 * 5 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 
 * Sample Output-1:
 * ----------------
 * 2
 * 
 * 
 * Sample Input-2:
 * ---------------
 * 5 6
 * 0 1
 * 0 2
 * 2 3
 * 1 2
 * 1 4
 * 2 4
 * 
 * Sample Output-2:
 * ----------------
 * 1
 * 
 */

import java.util.*;

public class U1_SP1_Connected_Components_DFS {
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
        Scanner sc = new Scanner(System.in);

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

        sc.close();
    }
}