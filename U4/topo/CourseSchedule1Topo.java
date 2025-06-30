import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CourseSchedule1Topo {
    public static boolean canFinish(int v, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(v);
        int[] indegree = new int[v];

        for (int i = 0; i < v; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbour : graph.get(node)) {
                indegree[neighbour]--;

                if (indegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

        for (int i = 0; i < v; i++) {
            if (indegree[i] != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int v = sc.nextInt(), e = sc.nextInt();
            int[][] edges = new int[e][2];
            for (int i = 0; i < e; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }
            boolean result = canFinish(v, edges);
            System.out.println(result ? "YES" : "NO");
        }
    }
}
