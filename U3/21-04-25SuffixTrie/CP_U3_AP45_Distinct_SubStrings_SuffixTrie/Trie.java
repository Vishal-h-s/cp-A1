// A communication researcher is analyzing a trail of signal frequencies encoded as a lowercase string. 
// Each character in the string represents a frequency tone recorded in a timeline.

// To evaluate the diversity of communication, the researcher wants 
// to count how many distinct contiguous frequency patterns (substrings) are present in the entire signal trail. 
// These patterns can be of any length and must be unique.

// To accomplish this, you are tasked with writing an efficient algorithm using Trie (Prefix Tree) 
// based suffix structures to count all distinct substrings of the given signal trail.

// Input Format:
// -------------
// Line-1: A lowercase string s representing the frequency trail.

// Output Format:
// --------------
// Line-1: Print a single integer — the number of distinct substrings of the input string.

// Constraints:
// -------------
// -> 1 ≤ |s| ≤ 10^4
// -> s contains only lowercase English letters.


// Sample Input-1:
// ---------------
// abc

// Sample Output-1:
// ----------------
// 6

// Explanation:
// ------------
// The substrings are: "a", "b", "c", "ab", "bc", "abc".
// Total = 6 distinct substrings.


// Sample Input-1:
// ---------------
// abc

// Sample Output-1:
// ----------------
// 6

// Explanation:
// ------------
// The substrings are: "a", "b", "c", "ab", "bc", "abc".
// Total = 6 distinct substrings.


// Sample Input-2:
// ---------------
// aaa

// Sample Output-2:
// ----------------
// 3

// Explanation:
// ------------
// Substrings: "a", "aa", "aaa" → 3 unique substrings.


// import java.util.Arrays;
// import java.util.Scanner;

// public class DistinctSubstrings {
//     public static int countDistinctSubstrings(String s) {
//         int n = s.length();

//         // Step 1: Build Suffix Array
//         String[] suffixes = new String[n];
//         for (int i = 0; i < n; i++) {
//             suffixes[i] = s.substring(i);
//         }
//         Arrays.sort(suffixes);

//         // Step 2: Calculate LCP Array
//         int[] lcp = new int[n];
//         for (int i = 1; i < n; i++) {
//             lcp[i] = calculateLCP(suffixes[i - 1], suffixes[i]);
//         }

//         // Step 3: Calculate Total Distinct Substrings
//         int totalSubstrings = n * (n + 1) / 2;
//         int overlappingSubstrings = Arrays.stream(lcp).sum();
//         return totalSubstrings - overlappingSubstrings;
//     }

//     private static int calculateLCP(String s1, String s2) {
//         int i = 0;
//         while (i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)) {
//             i++;
//         }
//         return i;
//     }

//     public static void main(String[] args) {
//         Scanner sc=new Scanner(System.in);
//         String input = sc.next();
//         System.out.println("Number of distinct substrings: " + countDistinctSubstrings(input));
//         sc.close();
//     }
// }

// To find the count of distinct substring of a string using trie data structure

import java.util.Arrays;
import java.util.Scanner;

public class Trie {
    private Trie[] children;
    private boolean isEnd;
    // private int wordCount, prefixCount;

    public Trie() {
        children = new Trie[26];
        Arrays.fill(children, null);
        isEnd=false;
        // prefixCount = 0;
        // wordCount = 0;

    }

    public void insert(String word) {
        Trie cur = this;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (cur.children[index] == null)
                cur.children[index] = new Trie();
            cur = cur.children[index];
            // cur.prefixCount++;
        }
        cur.isEnd=true;
        // cur.wordCount++;
    }

    static int dfs(Trie node){
        if(node==null) return 0;
        int count=0;
        for(int idx=0;idx<26;idx++){
            if(node.children[idx]!=null){
                count+=dfs(node.children[idx])+1;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        Trie suffixTrie = new Trie();
        for (int idx = 0; idx < input.length(); idx++) {
            suffixTrie.insert(input.substring(idx));
        }        
        int count=dfs(suffixTrie);
        System.out.println(count);
        sc.close();
    }
}
