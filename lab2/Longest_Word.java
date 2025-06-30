
import java.util.*;

class Node {
    public Node[] children;
    public boolean end;

    Node() {
        children = new Node[26];
        end = false;
    }
}

public class Longest_Word {
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

        // recursively explore all children
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                currentWord.append((char) ('a' + i));// add the character to current word
                dfs(node.children[i], currentWord);// recur for child node
                currentWord.deleteCharAt(currentWord.length() - 1);
                // backtract for the loop to continue correctly
            }
        }

    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] words = sc.nextLine().split("\\s+");
            root = new Node();
            for (String word : words)
                insert(word);
            dfs(root, new StringBuilder());
            System.out.println(result);
            sc.close();

        }
    }
}