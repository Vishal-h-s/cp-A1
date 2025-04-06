
/*
Given a non-empty string s and an abbreviation abbr, 
return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

Notice that only the above abbreviations are valid abbreviations of the string "word". 
Any other string is not a valid abbreviation of "word".

Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Input Format:
-------------
Line-1: A string, S contains only lowercase letters
Line-2: A string, abbr contains lowercase letters and digits

Output Format:
--------------
Line-1: A boolean value.
Sample Input-1:
---------------
internationalization
i12iz4n

Sample Output-1:
---------------
true

Sample Input-2:
---------------
apple
a2e

Sample Output-2:
---------------
false

Time Complexity: O(n) where n=max(len(word),len(abbr))
Auxiliary Space:  O(1). */

import java.util.*;

public class SP7_Valid_Word_Abbreviation {
    static char[] abbreviation, word;

    static boolean testValidity() {
        int i = 0, j = 0;
        boolean isValid = false;
        while (i < word.length && j < abbreviation.length) {
            if (abbreviation[j] == '0') {
                isValid = false;
                break;
            }
            else if (word[i] == abbreviation[j]) {
                i++;
                j++;
                continue;
            } else {
                if (!Character.isDigit(abbreviation[j])) {
                    isValid = false;
                    break;
                }
                StringBuilder num = new StringBuilder();
                while (j < abbreviation.length && Character.isDigit(abbreviation[j])) {
                    num.append(abbreviation[j++]);
                }
                i += Integer.parseInt(num.toString());
            }
        }
        if (i == word.length && j == abbreviation.length)
            isValid = true;
        return isValid;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        word = sc.next().toCharArray();
        abbreviation = sc.next().toCharArray();

        System.out.println(testValidity());
        sc.close();
    }
}