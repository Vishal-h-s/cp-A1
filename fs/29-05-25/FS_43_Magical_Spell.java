/*
 * In the ancient land of Palindoria, wizards write magical spells as strings of lowercase letters. However, for a spell to be effective, each segment of the spell must read the same forward and backward.

Given a magical spell 'spell', your task is to partition it into sequences of valid magical spell segments. 

Your goal is to help the wizard discover all valid combinations of magical spell segmentations.

Sample Input-1:
---------------
aab
  
Sample Output-1:  
----------------
[[a, a, b], [aa, b]]

Sample Input-2:
--------------- 
a
  
Sample Output-2:  
----------------
[[a]]

Spell Constraints:
------------------
- The length of the spell is between 1 and 16 characters.  
- The spell contains only lowercase English letters.  

 */

import java.util.*;

public class FS_43_Magical_Spell {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String spell = sc.nextLine().trim();
        List<List<String>> result = partition(spell);
        System.out.println(result);
        sc.close();
    }

    public static List<List<String>> partition(String spell) {
        List<List<String>> result = new ArrayList<>();
        backtrack(spell, 0, new ArrayList<>(), result);
        return result;
    }

    public static void backtrack(String spell, int start, List<String> current, List<List<String>> result) {
        if (start == spell.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < spell.length(); end++) {
            if (isPalindrome(spell, start, end)) {
                current.add(spell.substring(start, end + 1));
                backtrack(spell, end + 1, current, result);
                current.remove(current.size() - 1);

            }
        }
    }
}
