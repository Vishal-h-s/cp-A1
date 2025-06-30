
import java.util.*;

class DisjointUnionSet {
    int[] parent;

    public DisjointUnionSet(int arraySize) {
        parent = new int[arraySize];
        for (int idx = 0; idx < arraySize; idx++) {
            parent[idx] = idx;
        }
    }

    // Returns representative of x's set
    int findParent(int num) {
        if (this.parent[num] != num)
            parent[num] = findParent(this.parent[num]);
        return parent[num];
    }

    // Unites the set that includes x and the set that includes x
    void union(int node1, int node2) {
        int parent1 = findParent(node1), parent2 = findParent(node2);
        if (parent1 != parent2)
            if (parent1 > parent2) {
                parent[parent1] = parent2;
            } else {
                parent[parent2] = parent1;
            }
    }
}

public class Smallest_Equivalent_String {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            StringBuilder result = new StringBuilder();
            char[] str1 = sc.next().toCharArray(), str2 = sc.next().toCharArray(), str = sc.next().toCharArray();

            DisjointUnionSet dus = new DisjointUnionSet(26);
            for (int idx = 0; idx < str1.length; idx++) {
                dus.union(str1[idx] - 'a', str2[idx] - 'a');
            }

            for (char character : str) {
                result.append((char) ('a' + dus.parent[character - 'a']));
            }

            System.out.println(result);
            sc.close();
        }
    }
}