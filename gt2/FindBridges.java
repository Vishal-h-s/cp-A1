import java.util.*;

public class FindBridges {
    static int time=0; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nv = sc.nextInt();
        int ne = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < ne; i++) {
            edges.add(Arrays.asList(sc.nextInt(),sc.nextInt()));
        }

        List<List<Integer>> result = findBridges(nv, edges);
        for (List<Integer> bridge : result) {
            System.out.println(bridge.get(0) + " " + bridge.get(1));
        }
        sc.close();
    }

    static List<List<Integer>> findBridges(int nv, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < nv; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.size(); i++) {
            int v1 = edges.get(i).get(0), v2 = edges.get(i).get(1);
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
        }

        // boolean[] visited = new boolean[nv];
        int[] discovery = new int[nv], low = new int[nv];

        Arrays.fill(discovery, -1);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nv; i++) {
            if (discovery[i]==-1) {
                dfs(i, discovery, low, -1, result, adj);
            }
        }
        return result;
    }

    static void dfs(int node, int[] discovery, int[] low,int parent, List<List<Integer>> result, List<List<Integer>> adj) {
        // visited[node] = true;
        discovery[node] = low[node] = time++;
        for (int neighbour : adj.get(node)) {
            if (discovery[neighbour]==-1) {
                // parent = node;
                dfs(neighbour, discovery, low, node, result, adj);
                low[node] = Math.min(low[node], low[neighbour]);

                if (low[neighbour] > discovery[node]) {
                    result.add(Arrays.asList(node, neighbour));
                }
            } else if (neighbour != parent) {
                low[node] = Math.min(low[node], discovery[neighbour]);
            }
        }
    }
}
