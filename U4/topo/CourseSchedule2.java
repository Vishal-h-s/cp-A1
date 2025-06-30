
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CourseSchedule2 {
    static ArrayList<Integer> findOrder(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            adj.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                queue.offer(i);

        ArrayList<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);
            for (int neighbour : adj.get(current)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0)
                    queue.add(neighbour);
            }
        }

        if (order.size() == n)
            return order;
        return new ArrayList<>();
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int v = sc.nextInt(), e = sc.nextInt();
            int[][] edges = new int[e][2];
            for (int i = 0; i < e; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }
            ArrayList<Integer> result = findOrder(v, edges);
            System.out.println(result);
        }
    }
}
