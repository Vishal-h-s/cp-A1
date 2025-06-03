import java.util.*;

class SuffixTrie {
    static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];

        // Insert a suffix into the trie
        void insertSuffix(String suffix) {
            if (suffix.isEmpty()) return;
            int index = suffix.charAt(0) - 'a';
            if (children[index] == null) {
                children[index] = new TrieNode();
            }
            children[index].insertSuffix(suffix.substring(1));
        }

        // Count the total number of nodes in the trie
        int countNodes() {
            int count = 1; // Count the current node
            for (TrieNode child : children) {
                if (child != null) {
                    count += child.countNodes();
                }
            }
            return count;
        }
    }

    private final TrieNode root = new TrieNode();

    // Constructor to build the suffix trie
    SuffixTrie(String text) {
        for (int i = 0; i < text.length(); i++) {
            root.insertSuffix(text.substring(i));
        }
    }

    // Count distinct substrings in the input string
    int countDistinctSubstrings() {
        return root.countNodes() - 1; // Subtract 1 to exclude the root node
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        SuffixTrie suffixTrie = new SuffixTrie(input);
        System.out.println("Count of distinct substrings: " + suffixTrie.countDistinctSubstrings());
        sc.close();
    }
}