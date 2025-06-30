import java.util.*;

class Graph {
    private final int V;
    private final List<List<Integer>> adjLst;
    private final int[] indegree;

    // Build graph and indegree array
    private void build(int[][] edges) {
        for (int i = 0; i <= V; i++) {
            adjLst.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjLst.get(u).add(v);
            indegree[v]++;
        }
    }

    public Graph(int V, int[][] edges) {
        this.V = V;
        this.adjLst = new ArrayList<>();
        this.indegree = new int[V + 1];  // Courses are 1-indexed
        build(edges);
    }

    public int minNumberOfSemesters(int k) {
        Queue<Integer> queue = new LinkedList<>();

        // Initially push all courses with no prerequisites
        for (int i = 1; i <= V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int semesters = 0;
        int taken = 0;

        while (!queue.isEmpty()) {
            int canTake = Math.min(queue.size(), k);
            List<Integer> nextBatch = new ArrayList<>();

            // Take up to k courses this semester
            for (int i = 0; i < canTake; i++) {
                int course = queue.poll();
                taken++;

                for (int neighbor : adjLst.get(course)) {
                    if (--indegree[neighbor] == 0) {
                        nextBatch.add(neighbor);
                    }
                }
            }

            // Add newly unlocked courses to the queue
            queue.addAll(nextBatch);
            semesters++;
        }

        // If not all courses could be taken (cycle), return -1
        return (taken == V) ? semesters : -1;
    }
}

public class ParallelCourses2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of courses: ");
            int n = sc.nextInt();

            System.out.print("Enter number of relations (edges): ");
            int e = sc.nextInt();

            int[][] edges = new int[e][2];

            System.out.println("Enter prerequisite relations (u v):");
            for (int i = 0; i < e; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            System.out.print("Enter max courses per semester (k): ");
            int k = sc.nextInt();

            Graph g = new Graph(n, edges);
            int result = g.minNumberOfSemesters(k);
            System.out.println("Minimum semesters required: " + result);
        }
    }
}
