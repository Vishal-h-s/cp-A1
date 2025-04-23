/*
 * In a school, the students are given an array of strings words[]. 
Students have to find the longest string in words[] 
such that every prefix of it is also in words.

For example, let words = ["a", "app", "ap","appl", "apply"]. 
The string "apply" has prefixes "ap","app","appl" and "a", all of which are in words.

Your task is the find and return the longest string in words as described above.

If there is more than one string with the same length, return the lexicographically
smallest one, and if no string exists, return "".

Input Format
-------------
Line1: Space spearated strings

Output Format
--------------
Line-1: A string 

Sample Input-1:
---------------
k kmi km kmit

Sample Output-1:
----------------
kmit

Explanation:
------------
"kmit" has prefixes "kmi", "km", "k" and all of them appear in words.


Sample Input-2:
---------------
t tup tupl tu tuple tupla

Sample Output-2:
----------------
tupla

Explanation:
------------
Both "tuple" and "tupla" have all their prefixes in words.
However, "tupla" is lexicographically smaller, so we return that.


Sample Input-3:
---------------
abc bc ab abcd

Sample Output-3:
----------------
""

*/

import java.util.*;

public class Node {
    public Node[] children;
    public boolean end;

    Node() {
        children = new Node[26];
        end = false;
    }
}

public class CP_U3_SP5_Longest_Word {
    static Node root;
    static String result = "";

    public static void insert(String word) {
        Node cur = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new Node();
            cur = cur.children[index];
        }
        cur.end = true;
    }

    // static String solution(){
    // result="";
    // dfs(new StringBuilder());
    // return result;
    // }

    static void dfs(Node node, StringBuilder currentWord) {
        // if current node is not end of a word, stop exploring
        if (!node.end && currentWord.length() > 0)
            return;

        // update the result if the current word is valid
        String candidate = currentWord.toString();
        if (candidate.length() > result.length()
                || (candidate.length() == result.length() && candidate.compareTo(result) < 0)) {
            result = candidate;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split("\\s+");
        root = new Node();
        for (String word : words)
            insert(word);
        String result = "";
        dfs(root, new StringBuilder());
        System.out.println(result);
        sc.close();

    }
}