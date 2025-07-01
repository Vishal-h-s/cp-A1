import java.util.*;

public class TopoDFS {

    private static void dfs(int v, List<Integer>[] adj, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        for (int i : adj[v]) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }
        stack.push(v);
    }

    static List<Integer>[] constructAdj(int v, int[][] edges) {
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new ArrayList[v];

        for (int i = 0; i < v; i++)
            adj[i] = new ArrayList<>();

        for (int[] edge : edges)
            adj[edge[0]].add(edge[1]);
        return adj;
    }

    static int[] topologicalSort(int v, int[][] edges) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        List<Integer>[] adj = constructAdj(v, edges);

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        int[] result = new int[v];
        int index = 0;
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
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
            for (int node : result) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

}