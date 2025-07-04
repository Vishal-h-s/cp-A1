import java.util.Arrays;
import java.util.Scanner;

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
        return node != null && node.prefixCount > 0;
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

    // @Override
    // public String toString() {
    // StringBuilder string = new StringBuilder();
    // print(this, string, new StringBuilder());
    // return string.toString();
    // }

    // private void print(Trie node, StringBuilder string, StringBuilder
    // currentWord) {
    // if (node == null)
    // return;
    // if (node.wordCount > 0)
    // string.append(currentWord).append('\n');

    // for (int idx = 0; idx < 26; idx++) {
    // if (node.children[idx] != null) {
    // currentWord.append((char) ('a' + idx));
    // print(node.children[idx], string, currentWord);
    // currentWord.deleteCharAt(currentWord.length() - 1);
    // }
    // }
    // }
    public void printSuffixes() {
        char[] str = new char[100]; // Adjust size based on your needs
        printUtil(this, str, 0);
    }

    private boolean isLeafNode(Trie node) {
        if (node == null)
            return false;
        for (Trie child : node.children) {
            if (child != null)
                return false;
        }
        return true;
    }

    private void printUtil(Trie root, char[] str, int level) {
        // If node is leaf node, it indicates end of string
        if (isLeafNode(root)) {
            // Add null terminator and print
            for (int k = level; k < str.length; k++) {
                str[k] = '\0';
            }
            // Print only up to the level where we added characters
            System.out.println(new String(str, 0, level));
            return;
        }

        // Check all possible children
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                // Add current character to string
                str[level] = (char) (i + 'a');
                // Recur for child
                printUtil(root.children[i], str, level + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        Trie suffixTrie = new Trie();
        for (int idx = 0; idx < input.length(); idx++) {
            suffixTrie.insert(input.substring(idx));
        }
        suffixTrie.printSuffixes();
        sc.close();
    }
}
