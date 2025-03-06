
/*
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
        
Example 1:
input = 
1432219
132219
12219 1219

3          
output = 1219
        
num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.


Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

 */

import java.util.*;

public class FS_AP6_RemoveKdigits {
    static String solve(StringBuilder input, int remove) {
        if (remove == input.length())
            return "0";
        int curr = 0,
                prev = curr - 1,
                next = curr + 1;
        while (remove > 0) {
            prev = curr - 1;
            next = curr + 1;
            int key = remove > 0 && input.charAt(curr) < input.charAt(next) ? next : curr;
            input.deleteCharAt(key);
            remove--;
            if (remove > 0 && input.charAt(curr) > input.charAt(next) && prev >= 0) {
                key = input.charAt(prev) < input.charAt(next) ? next : prev;
                input.deleteCharAt(key);
                remove--;
            }
            curr++;
        }
        prev=0;
        while(prev<curr && input.charAt(prev)=='0'){
            input.deleteCharAt(prev++);
        }
        return input.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder input = new StringBuilder(sc.next());
        int remove = sc.nextInt();
        System.out.println(solve(input, remove));
        sc.close();
    }
}
