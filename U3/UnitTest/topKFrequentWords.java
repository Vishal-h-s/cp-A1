/*
 * Top K frequent words.
We are given an array of strings words and an integer k, we have to return k strings which has
highest frequency.
We need to return the answer which should be sorted by the frequency from highest to lowest
and the words which has the same frequency sort them by their alphabetical order.
Example-1: 
Input: 
words = ["i","love","writing","i","love","coding"], k = 2 
Output: 
["i","love"] 
Explanation: "i" and "love" are the two most frequent words
 */

package U3.UnitTest;

import java.util.*;

class Node {
    Node[] children;
    int frequency;

    Node() {
        children = new Node[26];
        frequency = 0;
    }
}

public class topKFrequentWords {
    static Node root;

    static void insert(String word) {
        Node cur = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new Node();
            cur = cur.children[index];
        }
        cur.frequency++;
    }

    private static int getFrequency(String word) {
        Node cur = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                return 0;
            cur = cur.children[index];
        }
        return cur.frequency;
    }

    public static List<String> topKFrequent(String[] words, int k) {
        root = new Node();
        for (String word : words)
            insert(word);
        // use a priority queue to sort by frequency and lexicography
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int freqCompare = getFrequency(b) - getFrequency(a);
            if (freqCompare == 0)
                return a.compareTo(b);
            return freqCompare;
        });
        // add all unique words to the pq
        for (String word : words) {
            if (!pq.contains(word)) {
                pq.offer(word);
            }
        }
        // extract the top k words
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++)
            result.add(pq.poll());
        return result;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] words=sc.nextLine().split("\\s+");
        int k=sc.nextInt();
        List<String> result=topKFrequent(words,k);
        System.out.println(result);
        sc.close();
    }
}