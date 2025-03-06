
/*
 * Clavius, the person who suggested grouping of data using brackets.
He has suggested that the group of brackets should be Well-Formed.

A string is said to be Well-Formed, if:
    - Any open bracket must have corresponding two consecutive close brackets.
    - Open bracket must appear before the corresponding close brackets.

You will be given a string B, consists of square brackets only '[' , ']'.
In one operation, you can insert one square bracket at any position of B.
i.e., Given B = [[]]], in one operation you can add a open square bracket,
now B can be Well-Formed string, if you add at index 2,3,4 or 5 => [[]]]]

Your task is to return the least number of operations required,
to make B as a Well-Formed string.

Input Format:
-------------
A string, B contains only characters '[', ']'

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
[]]][

Sample Output-1:
----------------
4


Sample Input-2:
---------------
[]][[]]

Sample Output-2:
----------------
2

*/

import java.util.*;

public class FS_AP4_Closed_Brackets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] brackets = sc.next().toCharArray();

        int opened = 0, insertions = 0;
        for(int idx=0;idx < brackets.length;idx++) {
            if (brackets[idx] == '[') {
                opened++;
            } else {
                if (opened == 0) {
                    insertions++;
                }else{
                    opened--;
                }
                if (idx < brackets.length - 1 && brackets[idx + 1] == ']') {
                    // opened--; why not??
                    idx+=1;
                } else {
                    insertions++;
                }
            }
            // idx+=1;
        }
        if (opened > 0) {
            insertions += (opened << 1);
        }

        System.out.println(insertions);
        sc.close();
    }
}