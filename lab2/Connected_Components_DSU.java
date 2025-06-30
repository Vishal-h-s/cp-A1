
import java.util.*;
public class Connected_Components_DSU{
    static int[] parent;
    static int findParent(int node){
        if(parent[node]!=node) parent[node]=findParent(node);
        return parent[node];
    }
    static void union(int v1,int v2){
        int p1=findParent(v1),p2=findParent(v2);
        if(p1!=p2){
            parent[p1]=p2;
        }
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int nv=sc.nextInt();
            int ne=sc.nextInt();
            int result=nv;
            parent=new int[nv];
            for(int idx=0;idx<nv;idx++)parent[idx]=idx;
            for(int idx=0;idx<ne;idx++){
                int v1=sc.nextInt(),v2=sc.nextInt();
                int p1=findParent(v1), p2=findParent(v2);
                if(p1!=p2){
                    result--;
                    union(v1,v2);
                }
            }
            System.out.println(result);
            sc.close();
        }
    }
}