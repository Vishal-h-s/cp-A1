/*
 * Given two integers left and right, return the count of numbers in the inclusive range [left, right]
 having a prime number of set bits in their binary representation.

Recall that the number of set bits an integer has the number of 1's present when written in binary.

For example, 21 written in binary is 10101, which has 3 set bits.
 
Input Format:
-------------
Line-1: Two separated integers

Output Format:
--------------
Line-1: An integer

Sample Input-1:
---------------
6  10

Sample Output-1:
---------------
4

Explanation:
------------
6  -> 110 (2 set bits, 2 is prime)
7  -> 111 (3 set bits, 3 is prime)
8  -> 1000 (1 set bit, 1 is not prime)
9  -> 1001 (2 set bits, 2 is prime)
10 -> 1010 (2 set bits, 2 is prime)
4 numbers have a prime number of set bits.

Sample Input-2:
---------------
10 15

Sample Output-2:
---------------
5

Explanation:
------------
10 -> 1010 (2 set bits, 2 is prime)
11 -> 1011 (3 set bits, 3 is prime)
12 -> 1100 (2 set bits, 2 is prime)
13 -> 1101 (3 set bits, 3 is prime)
14 -> 1110 (3 set bits, 3 is prime)
15 -> 1111 (4 set bits, 4 is not prime)
5 numbers have a prime number of set bits.

 */

import java.util.*;

public class AP25_PrimeSet_Bits {
    static int start, end, result;

    static int isPrime(int num) {
        if (num <= 1)
            return 0;
        if (num <= 3)
            return 1;
        if ((num & 1) == 0 || num % 3 == 0)
            return 0;
        for (int iter = 5; iter * iter <= num; iter += 6) {
            if ((num % iter) == 0 || (num % (iter + 2)) == 0) {
                return 0;
            }
        }
        return 1;
    }

    static int countSetBits(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1)
                count++;
            num >>= 1;
        }
        return count;
    }

    static void solution1() {
        result = 0;
        for (int idx = start; idx <= end; idx++) {
            int count = countSetBits(idx);
            result += isPrime(count);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        start = sc.nextInt();
        end = sc.nextInt();
        solution1();
        System.out.println(result);
        sc.close();
    }
}