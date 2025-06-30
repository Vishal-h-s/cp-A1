/*
 * Vihaar is working with strings. 
He is given two strings A and B, and another string T,
where the length of A and B is same.

You can find the relative groups of letters from A and B,
using the following rule set:
- Equality rule: 'p' == 'p'
- Symmetric rule: 'p' == 'q' is same as 'q' == 'p'
- Transitive rule: 'p' == 'q' and 'q' == 'r' indicates 'p' == 'r'.

Vihaar has to form the relatively smallest string of T,
using the relative groups of letters.

For example, if A ="pqr" and B = "rst" , 
then we have 'p' == 'r', 'q' == 's', 'r' == 't' .

The relatives groups formed using above rule set are as follows: 
[p, r, t] and [q,s] and  String T ="tts", then relatively smallest string is "ppq".

You will be given the strings A , B and T.
Your task is to help Vihaar to find the relatively smallest string of T.


Input Format:
-------------
Three space separated strings, A , B and T

Output Format:
--------------
Print a string, relatively smallest string of T.


Sample Input-1:
---------------
kmit ngit mgit

Sample Output-1:
----------------
ggit

Explanation: 
------------
The relative groups using A nd B are [k, n], [m, g], [i], [t] and
the relatively smallest string of T is "ggit"


Sample Input-2:
---------------
attitude progress apriori

Sample Output-2:
----------------
aaogoog

Explanation: 
------------
The relative groups using A nd B are [a, p], [t, r, o], [i, g] and [u, e, d, s]
the relatively smallest string of T is "aaogoog"
 */

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

public class U5_SP1_Smallest_Equivalent_String {

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