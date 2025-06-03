/*
 * there is an input string STR(without any space) and an array of 
strings words[]. Print all the pairs of indices [s, e] where s, e are starting 
index and ending index of every string in words[] in the input string STR.

Note: Print the pairs[s, e] in sorted order.
(i.e., sort them by their first coordinate, and in case of ties sort them by 
their second coordinate).

Input Format
------------
Line-1: string STR(without any space)
Line-2: space separated strings, words[]

Output Format
-------------
Print the pairs[s, e] in sorted order.

Sample Input-1:
---------------
thekmecandngitcolleges
kmec ngit colleges

Sample Output-1:
----------------
3 6
10 13
14 21

Sample Input-2:
---------------
xyxyx
xyx xy

Sample Output-2:
----------------
0 1
0 2
2 3
2 4

Explanation: 
------------
Notice that matches can overlap, see "xyx" is found at [0,2] and [2,4].

Sample Input-3:
---------------
kmecngitkmitarecsecolleges
kmit ngit kmec cse

Sample Output-3:
----------------
0 3
4 7
8 11
15 17

 */

import java.util.*;

class Node {
    Node[] children;
    boolean isEnd;

    Node() {
        children = new Node[26];
        isEnd = false;
    }
}

public class CP_U3_SP4_IndexPairs {
    static int len;
    static String[] words;
    static String text;
    static Node root;

    static void insert(String word) {
        Node cur = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new Node();
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        text = sc.nextLine();
        words = sc.nextLine().split("\\s+");
        len = words.length;
        root = new Node();
        for (String word : words) {
            insert(word);
        }
        Node cur = root;
        List<int[]> indices = new ArrayList<>();
        int index = 0;
        for (int start = 0; start < text.length(); start++) {
            cur = root;
            int end = start;
            while (end < text.length()) {
                index = text.charAt(end) - 'a';
                if (cur.children[index] == null)
                    break;
                cur = cur.children[index];
                if (cur.isEnd)
                    indices.add(new int[] { start, end });
                end++;
            }
        }

        for (int[] ind : indices) {
            System.out.println(Arrays.toString(ind));
        }
        sc.close();
    }
}