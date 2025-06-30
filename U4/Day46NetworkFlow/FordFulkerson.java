
import java.util.Scanner;


public class FordFulkerson{
    static int visitedToken=1;

    public static int fordFulkerson(int[][]caps,int source, int sink){
        int n=caps.length;
        int[]visited=new int[n];
        // boolean[] minCut=new boolean[n];

        for(int maxFlow=0;;){
            int flow=dfs(caps, visited, source, sink, Integer.MAX_VALUE);
            visitedToken++;

            maxFlow+=flow;

            if(flow==0){
                return maxFlow;
            }
        }
    }

    private static int dfs(int[][]caps, int[]visited, int node ,int sink, int flow){
        if(node==sink) return flow;

        int[]cap=caps[node];
        visited[node]=visitedToken;

        for(int i=0;i<cap.length;i++){
            if(visited[i]!=visitedToken && cap[i]>0){
                if(cap[i]<flow) flow=cap[i];
                int bottleneck=dfs(caps, visited, i, sink , flow);

                if(bottleneck>0){
                    caps[node][i]-=bottleneck;
                    caps[i][node]+=bottleneck;
                    return bottleneck;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        try(Scanner sc=new Scanner(System.in)){
            System.out.println("Enter number of nodes");
            int vertices=sc.nextInt();
            int[][] caps=new int[vertices][vertices];
            System.out.println("Enter capacity matrix");
            for(int i=0;i<vertices;i++){
                for(int j=0;j<vertices;j++){
                    caps[i][j]=sc.nextInt();
                }
            }
            System.out.println("Enter source");
            int source=sc.nextInt();
            System.out.println("Enter sink");
            int sink=sc.nextInt();
            System.out.println("Calculating max flow");
            int maxFlow=fordFulkerson(caps, source, sink);
            System.out.println("MaxFlow = "+maxFlow);
        }
    }

}