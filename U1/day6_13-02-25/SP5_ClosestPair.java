/*
Two brothers are playing a game based on two sorted lists of numerical elements and a target sum.

The first brother provides:
-> Two sorted lists of integers.
-> A target sum.

The second brother's task is to find and return the closest pair of elements (one from each list) 
whose sum is closest to the given target.

Rules:
------
Each pair must consist of one element from each list.
If multiple pairs have the same closest sum, return any one valid pair.
The input lists are already sorted in ascending order.
The result must be printed as a comma-separated pair.

Input Format:
-------------
Line 1: An integer N1, representing the size of the first list.
Line 2: N1 space-separated integers, representing elements of the first sorted list.
Line 3: An integer N2, representing the size of the second list.
Line 4: N2 space-separated integers, representing elements of the second sorted list.
Line 5: An integer X, representing the target sum.

Output Format:
--------------
Line-1: Print a comma-separated pair (a, b), where a is from list_1 and b is from list_2, such that their sum is closest to the target sum.

Sample Input-1:
---------------
4
1 4 5 7
4
10 20 30 40
32

Sample Output-1:
----------------
1,30

Explanation:
------------
The closest pair to 32 is (1,30) → 1 + 30 = 31, which is the closest sum to 32.

Sample Input-2:
---------------
3
2 4 6
4
5 7 11 13
15

Sample Output-2:
----------------
2,13

Explanation:
------------
The closest pair to 15 is (2,13) → 2 + 13 = 15, which is an exact match.

 */

import java.util.*;

public class SP5_ClosestPair {
    static int[] a, b;
    static int n1, n2, target, result1, result2;

    static void solution() {
        int i = 0, j = n2 - 1;
        int min = Integer.MAX_VALUE, diff;
        while (i < n1 && j >= 0) {
            int sum = a[i] + b[j];

            if (sum == target) {
                result1 = a[i];
                result2 = b[j];
                break;
            }

            diff = Math.abs(sum - target);

            if (diff < min) {
                result1 = a[i];
                result2 = b[j];
                min = diff;
            }
            if (sum < target) {
                System.out.println(i + " " + j);
                i++;

            } else {
                System.out.println(i + " " + j);
                j--;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n1 = sc.nextInt();
        a = new int[n1];
        for (int i = 0; i < n1; i++)
            a[i] = sc.nextInt();

        n2 = sc.nextInt();
        b = new int[n2];
        for (int i = 0; i < n2; i++)
            b[i] = sc.nextInt();

        target = sc.nextInt();
        solution();

        System.out.println(result1 + "," + result2);
        sc.close();
    }
}