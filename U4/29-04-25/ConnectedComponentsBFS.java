/*
 * There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1

 */

import java.util.*;

public class ConnectedComponentsBFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of cities (N) and routes (M)
        int N = sc.nextInt();
        int M = sc.nextInt();

        // Build the adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            graph.get(city1).add(city2);
            graph.get(city2).add(city1);
        }

        // Array to track visited cities
        boolean[] visited = new boolean[N];
        int regions = 0;

        // Perform BFS for each unvisited city
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
                regions++;
            }
        }

        // Print the number of regions
        System.out.println(regions);
    }

    // BFS function to traverse the graph
    private static void bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int neighbor : graph.get(city)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}