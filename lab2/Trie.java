
import java.util.Arrays;

public class Trie {
    private Trie[] children;
    // private boolean isEnd;
    private int wordCount, prefixCount;

    public Trie() {
        children = new Trie[26];
        Arrays.fill(children, null);
        // isEnd=false;
        prefixCount = 0;
        wordCount = 0;

    }

    public void insert(String word) {
        Trie cur = this;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new Trie();
            cur = cur.children[index];
            cur.prefixCount++;
        }
        // cur.isEnd=true;
        cur.wordCount++;
    }

    private Trie searchPrefix(String prefix) {
        Trie cur = this;
        for (char letter : prefix.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                return null;
            cur = cur.children[index];
        }
        return cur;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        // return node!=null && node.isEnd;
        return node != null && node.wordCount > 0;
    }

    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        // return node!=null;
        return node != null && node.prefixCount > 0 ;
    }

    public int wordCount(String word) {
        Trie node = searchPrefix(word);
        return node == null ? 0 : node.wordCount;
    }

    public int prefixCount(String prefix) {
        Trie node = searchPrefix(prefix);
        return node == null ? 0 : node.prefixCount;
    }

    public void delete(String word) {
        Trie cur = this;
        Trie[] stack = new Trie[word.length()];
        int[] indices = new int[word.length()];
        int idx = 0;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                return;
            stack[idx] = cur;
            indices[idx] = index;

            cur = cur.children[index];
            cur.prefixCount--;
            idx++;

            if (cur.prefixCount == 0) {
                cur.children = null;
            }
        }
        cur.wordCount--;
        if (cur.wordCount == 0) {
            cur.children = null;
        }

        for (int jdx = word.length() - 1; jdx >= 0; jdx--) {
            Trie parent = stack[jdx];
            int index = indices[jdx];
            if (parent.children[index].prefixCount == 0 && parent.children[index].wordCount == 0) {
                parent.children[index] = null;
            } else {
                break;
            }
        }
    }

    public void print(Trie node, String prefix) {
        for (int idx = 0; idx < 26; idx++) {
            if (node.children != null && node.children[idx] != null) {
                char letter = (char) ('a' + idx);
                int count = node.children[idx].wordCount;
                if (count > 0) {
                    // Print the prefix and word count
                    System.out.println(prefix + letter + ": " + count);
                }
                // Recursively print the children
                print(node.children[idx], prefix + letter);
            }
        }
    }
}