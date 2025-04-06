/*
 * A string is good if there are no repeated characters.
Given a string s, return the number of good substrings of length three in s.
Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
A substring is a contiguous sequence of characters in a string.

Sample Input-1:
---------------
xyzzaz

Sample Output-1:
----------------
1

Explanation: 
------------
There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
The only good substring of length 3 is "xyz".

Sample Input-2:
---------------
aababcabc

Sample Output-2:
----------------
4

Explanation: 
------------
There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc".
 */

import java.util.*;

public class AP8_GoodStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the string
        String s = sc.next();

        // Input the dynamic window size (if needed, replace with a fixed size like 3)
        int k = sc.nextInt();

        System.out.println(countGoodSubstrings(s, k));
        sc.close();
    }

    public static int countGoodSubstrings(String s, int k) {
        if (s.length() < k) {
            return 0; // If the string is shorter than the window size, return 0
        }

        Map<Character, Integer> charCount = new HashMap<>();
        int count = 0;

        // Initialize the first window
        for (int i = 0; i < k; i++) {
            charCount.put(s.charAt(i), charCount.getOrDefault(s.charAt(i), 0) + 1);
        }

        // Check if the first window is "good"
        if (charCount.size() == k) {
            count++;
        }

        // Slide the window across the string
        for (int i = k; i < s.length(); i++) {
            // Add the new character to the window
            char newChar = s.charAt(i);
            charCount.put(newChar, charCount.getOrDefault(newChar, 0) + 1);

            // Remove the character that is sliding out of the window
            char oldChar = s.charAt(i - k);
            if (charCount.get(oldChar) == 1) {
                charCount.remove(oldChar);
            } else {
                charCount.put(oldChar, charCount.get(oldChar) - 1);
            }

            // Check if the current window is "good"
            if (charCount.size() == k) {
                count++;
            }
        }

        return count;
    }
}