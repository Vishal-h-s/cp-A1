
import java.util.*;

class Node {
    Node[] children;
    boolean isEnd;

    Node() {
        children = new Node[26];
        isEnd = false;
    }
}

public class IndexPairs {
    // static int len;
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

        try (Scanner sc = new Scanner(System.in)) {
            text = sc.nextLine();
            words = sc.nextLine().split("\\s+");
            // len = words.length;
            root = new Node();
            for (String word : words) {
                insert(word);
            }
            List<int[]> indices = new ArrayList<>();
            for (int start = 0; start < text.length(); start++) {
                Node cur = root;
                int end = start;
                while (end < text.length()) {
                    int index = text.charAt(end) - 'a';
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
}