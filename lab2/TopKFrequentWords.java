
import java.util.PriorityQueue;
import java.util.Scanner;

public class TopKFrequentWords {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord;
        String word = "";
        int freq;
    }
    
    private final TrieNode root = new TrieNode();
    
    private void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.endOfWord = true;
        node.freq++;
        node.word = word;
    }
    
    private void dfs(TrieNode node, PriorityQueue<TrieNode> pq) {
        if (node == null) return;
        if (node.endOfWord) pq.offer(node);
        for (TrieNode child : node.children) dfs(child, pq);
    }
    
    private void printTopKWords(String[] words, int k) {
        for (String word : words) insert(word);
        
        PriorityQueue<TrieNode> pq = new PriorityQueue<>((a, b) -> 
            a.freq == b.freq ? a.word.compareTo(b.word) : b.freq - a.freq);
        
        dfs(root, pq);
        
        for (int i = 0; i < k; i++) System.out.print(pq.poll().word + " ");
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] words = sc.nextLine().split(",");
            int k = sc.nextInt();
            
            new TopKFrequentWords().printTopKWords(words,k);
        }
        
    }
}