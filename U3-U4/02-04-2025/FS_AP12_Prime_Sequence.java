/*
 * In a distant galaxy, there exists an ancient space station covered in a vast 
array of digital codes. These codes are believed to hold the key to unlocking 
powerful alien technology. The interstellar explorers call these codes "prime 
sequences."

The prime-sequence beauty of the digital code is defined as the number of prime 
sequences that meet the following conditions:
    -The prime sequence has a length of k.
    -The prime sequence is a divisor of the entire digital code.
    -The prime sequence is a prime number.

Given the digital code on the space station, represented as an integer code, and
the length of the prime sequences k, return the prime-sequence beauty of the code.

Note:
-----
Leading zeros in prime sequences are allowed.
0 is not a divisor of any value.
A prime sequence is a contiguous sequence of characters in the main digital code.

Input Format:
-------------
Line-1: space separated String and integer, code and K

Output Format:
-------------
An integer, the prime-sequence beauty of the code.


Sample Input-1:
---------------
239246 2 

Sample Output-1:
----------------
1 

Explanation:
------------
The following are the prime sequences of length k:
    "23" from "239246": 23 is a divisor of 239246 and is a prime number.
    "39" from "239246": 39 is not a divisor.
    "92" from "239246": 92 is not a divisor.
    "24" from "239246": 24 is is not a divisor 239246.
    "46" from "239246": 46 is a divisor of 239246 but is not a prime number.
    Therefore, the prime-sequence beauty is 1.

Sample Input-2:
---------------
24224 1

Sample Output-2:
----------------
3

 
*/

import java.util.*;

public class FS_AP12_Prime_Sequence {
    static int windowSize, num, result;
    static char[] digits;

    static boolean isPrime(int num) {
        if (num <= 1)
            return false;
        else if (num <= 3)
            return true;
        else if (num % 2 == 0 || num % 3 == 0)
            return false;
        else {
            for (int divisor = 5; divisor * divisor <= num; divisor += 6) {
                if (num % divisor == 0 || num % (divisor + 2) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValid(int seq) {
        return seq != 0 && num % seq == 0 && isPrime(seq);
    }

    static void solution() {
        result = 0;
        int len = digits.length;
        int power = (int) Math.pow(10, windowSize - 1); // 10^(windowSize - 1)

        // Initialize the first window
        int currentWindow = 0;
        for (int idx = 0; idx < windowSize; idx++) {
            currentWindow = currentWindow * 10 + (digits[idx] - '0');
        }

        // Check the first window
        if (isValid(currentWindow)) {
            result++;
        }

        // Slide the window
        for (int idx = windowSize; idx < len; idx++) {
            // Remove the leftmost digit and add the new digit
            currentWindow = (currentWindow % power) * 10 + (digits[idx] - '0');

            // Check the current window
            if (isValid(currentWindow)) {
                result++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String code = sc.next();
        windowSize = sc.nextInt();
        num = Integer.parseInt(code);
        digits = code.toCharArray();
        solution();
        System.out.println(result);
        sc.close();
    }
}