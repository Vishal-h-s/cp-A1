
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class KahnTopoBFS {
    static List<Integer>[] constructadj(int v, int[][] edges) {
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new ArrayList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new ArrayList<>();
        for (int[] edge : edges)
            adj[edge[0]].add(edge[1]);
        return adj;
    }

    static int[] topologicalSort(int v, int[][] edges) {
        List<Integer>[] adj = constructadj(v, edges);
        int[] indegree = new int[v];

        for (int i = 0; i < v; i++) {
            for (int neighbour : adj[i]) {
                indegree[neighbour]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[v];
        int index = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[index++] = node;

            for (int neighbour : adj[node]) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        if (index != v) {
            System.err.println("Graph is cyclic!");
            return new int[0];
        }

        return result;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int v = sc.nextInt(), e = sc.nextInt();
            int[][] edges = new int[e][2];
            for (int i = 0; i < e; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }
            int[] result = topologicalSort(v, edges);

            if (result.length > 0) {
                for (int i : result)
                    System.out.println(i + " ");
            }
        }
    }
}
