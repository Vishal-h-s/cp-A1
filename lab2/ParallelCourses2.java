
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Graph{
    private final int v;
    private final List<List<Integer>> adj;
    private final int[] indegree;

    Graph(int v, int[][]edges){
        this.v=v;
        this.adj=new ArrayList<>();
        this.indegree=new int[v+1];
        build(edges);
    }
    
    private void build(int[][]edges){
        for(int i=0;i<=v;i++){
            adj.add(new ArrayList<>());
        }

        for(int[]edge:edges){
            adj.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
    }

    public int minSemesters(int k){
        Queue<Integer> queue=new LinkedList<>();
        for(int i=1;i<=v;i++) if(indegree[i]==0) queue.offer(i);

        int semesters=0, taken=0;

        while(!queue.isEmpty()){
            int canTake=Math.min(queue.size(),k);
            List<Integer> nextBatch=new ArrayList<>();
            for(int i=0;i<canTake;i++){
                int course=queue.poll();
                taken++;

                for(int neighbor:adj.get(course)){
                    if(--indegree[neighbor]==0) nextBatch.add(neighbor);
                }
            }
            queue.addAll(nextBatch);
            semesters++;
        }
        return (taken==v)?semesters:-1;
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
            int result = g.minSemesters(k);
            System.out.println("Minimum semesters required: " + result);
        }
    }
}