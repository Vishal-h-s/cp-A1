// Program to implement Disjoint Set Data Structure.

/*
5 = number of friends
3 = number of relationships
2 = number of friendship check
input=5
3
0 2
4 2
3 1
2
4 0
1 0
output=4 is a friend of 0
1 is not a friend of 0

*/
import java.util.*;

class DisjointUnionSet {
    int[] rank, parent;
    int arraySize;

    public DisjointUnionSet(int arraySize) {
        rank = new int[arraySize];
        parent = new int[arraySize];
        this.arraySize = arraySize;
        makeSet();
    }

    // Creates n sets with single item in each
    void makeSet() {
        for(int idx=0;idx<this.arraySize;idx++){
            this.rank[idx]=1;
            this.parent[idx]=idx;
        }
    }

    // Returns representative of x's set
    int findParent(int num) {
        if(this.parent[num]!=num) return findParent(this.parent[num]);
        else return num;
    }

    // Unites the set that includes x and the set that includes x
    void union(int node1, int node2) {
        int parent1=findParent(node1), parent2=findParent(node2);
        if(rank[parent1]<rank[parent2]){
            parent[parent1]=parent2;
            rank[parent2]+=1;
        }
        else if(rank[parent1]>rank[parent2]){
            parent[parent2]=parent1;
            rank[parent1]+=1;
        }
        else{
            parent[parent1]=parent2;
            rank[parent2]+=1;
        }
    }
}

class test {
    static String stringify(int node){
        return Integer.toString(node);    
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arraySize = sc.nextInt();
        DisjointUnionSet dus = new DisjointUnionSet(arraySize);
        int inputSize=sc.nextInt();
        while(inputSize>0){
            int node1=sc.nextInt(), node2=sc.nextInt();
            dus.union(node1, node2);
            inputSize--;
        }

        int queries=sc.nextInt();
        StringBuilder result=new StringBuilder();
        while(queries>0){
            int node1=sc.nextInt(),node2=sc.nextInt();
            int parent1=dus.findParent(node1), parent2=dus.findParent(node2);
            
            result.append(parent1==parent2?stringify(node1)+" is a friend of "+stringify(node2):stringify(node1)+" is not a friend of "+stringify(node2)).append('\n');
            
            queries--;
        }
        System.out.println(result);
        sc.close();
    }
}
