
import java.util.Arrays;
import java.util.Scanner;

public class SuffixTrie {
    private SuffixTrie[] children;
    // private boolean isEndOfWord;
    private int wordCount, prefixCount;

    public SuffixTrie() {
        children = new SuffixTrie[26];
        Arrays.fill(children, null);
        // isEndOfWord = false;
        prefixCount = 0;
        wordCount = 0;
    }

    public void insert(String word) {
        SuffixTrie cur = this;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new SuffixTrie();
            cur = cur.children[index];
            cur.prefixCount++;
        }
        // cur.isEnd=true;
        cur.wordCount++;
    }

    private SuffixTrie searchPrefix(String prefix) {
        SuffixTrie cur = this;
        for (char letter : prefix.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                return null;
            cur = cur.children[index];
        }
        return cur;
    }

    public boolean search(String word) {
        SuffixTrie node = searchPrefix(word);
        // return node!=null && node.isEnd;
        return node != null && node.wordCount > 0;
    }

    public boolean startsWith(String prefix) {
        SuffixTrie node = searchPrefix(prefix);
        // return node!=null;
        return node != null && node.prefixCount > 0;
    }

    public int wordCount(String word) {
        SuffixTrie node = searchPrefix(word);
        return node == null ? 0 : node.wordCount;
    }

    public int prefixCount(String prefix) {
        SuffixTrie node = searchPrefix(prefix);
        return node == null ? 0 : node.prefixCount;
    }

    public void delete(String word) {
        SuffixTrie cur = this;
        SuffixTrie[] stack = new SuffixTrie[word.length()];
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
            SuffixTrie parent = stack[jdx];
            int index = indices[jdx];
            if (parent.children[index].prefixCount == 0 && parent.children[index].wordCount == 0) {
                parent.children[index] = null;
            } else {
                break;
            }
        }
    }

    public void printSuffixes() {
        StringBuilder str = new StringBuilder(); // Use StringBuilder instead of char[]
        printUtil(this, str);
    }

    @SuppressWarnings("unused")
    private boolean isLeafNode(SuffixTrie node) {
        if (node == null)
            return false;
        for (SuffixTrie child : node.children) {
            if (child != null)
                return false;
        }
        return true;
    }

    private void printUtil(SuffixTrie root, StringBuilder str) {
        if (root.wordCount > 0) {
            System.out.println(str.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                str.append((char) ('a' + i));
                printUtil(root.children[i], str);
                str.setLength(str.length() - 1); // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.next();
            SuffixTrie suffixTrie = new SuffixTrie();

            // Insert all suffixes
            for (int i = 0; i < input.length(); i++) {
                String suffix = input.substring(i);
                suffixTrie.insert(suffix);
            }

            suffixTrie.printSuffixes();
        }
    }
}