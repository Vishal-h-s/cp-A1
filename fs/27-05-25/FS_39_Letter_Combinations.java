/*
 * Imagine you're a top-secret agent receiving an encrypted directive from headquarters. 
The message comes as a string of digits, 
and each digit (from 2 to 9) is a cipher for a set of potential code letters. 

To uncover the true instruction, you must translate the string into 
every possible combination of letters by substituting each digit with 
its corresponding set of letters. The final decoded messages listed in lexicographycal order.
Below is the mapping of digits to letters (as found on a traditional telephone keypad):                     

| Digit| Letters    |
|------|----------- |
| 2    | a, b, c    |
| 3    | d, e, f    |
| 4    | g, h, i    |
| 5    | j, k, l    |
| 6    | m, n, o    |
| 7    | p, q, r, s |   
| 8    | t, u, v    |
| 9    | w, x, y, z |

Note: The digit 1 does not correspond to any letters.

Input Format:
-------------
Line-1: A string, represents the message (2-9 inclusive)

Output Format:
--------------
Line-1: List of valid combination

Sample Input-1:
---------------
23

Sample Output-1:
----------------
[ad, ae, af, bd, be, bf, cd, ce, cf]


Sample Input-2: 
---------------
2

Sample Output-2:
----------------
[a, b, c]


Constraints:
------------
- 0 <= digits.length <= 4  
- Each digit in the input is between '2' and '9'.
 */

import java.util.*;

public class FS_39_Letter_Combinations {
    static final String[] KEYPAD = {
        "",    // 0
        "",    // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(String digits, int idx, StringBuilder sb, List<String> result) {
        if (idx == digits.length()) {
            result.add(sb.toString());
            return;
        }
        String letters = KEYPAD[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(digits, idx + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        List<String> combinations = letterCombinations(digits);
        System.out.println(combinations);
        sc.close();
    }
}