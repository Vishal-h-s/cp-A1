/*
There is a Game where a fighter consumes power[i] on the i-th hour. 

Given an integer k, for every consecutive sequence of k hours (power[i], 
power[i+1], ..., power[i+k-1] for all 0 <= i <= n-k), they look at T, 
the total power consumed during that sequence of k hours (power[i] + 
power[i+1] + ... + power[i+k-1]):

If T < lower, fighter performed poorly and lose 1 point; 
If T > upper, fighter performed well and gain 1 point;
Otherwise, fighter performed normally and there is no change in points.

Initially, the fighter has zero points. Return the total number of points the 
fighter has after consuming power for power.length hours.

Note that the total points can be negative.

 
Input Format
--------------
Line-1: An integer N, the arrray length.
Line-2: Array of integers, power[]
Line-3: Three integers -subarray length K, lower value and upper value

output Format
--------------
An integer total points

Sample Input-1:
---------------
4
7 10 0 0
2 1 12

Sample Output-1:
----------------
0

Explanation:
------------
Since k = 2, we consider subarrays of length 2.
power[0] + power[1] > upper so 1 point is gained.
lower <= power[1] + power[2] <= upper so no change in points.
power[2] + power[3] < lower so 1 point is lost.


Sample Input-2:
---------------
2
5 10
2 0 1

Sample Output-2:
----------------
1

Explanation:
------------
Since k = 2, we consider subarrays of length 2.
power[0] + power[1] > upper so 1 point is gained.
Example 3:

Sample Input-3:
---------------
5
1 2 3 4 5
1 3 3

Sample Output-3:
----------------
0

Explanation:
------------
Since k = 1, Since k = 2, we consider subarrays of length 1. and compare it 
to lower and upper. power[0] and power[1] are less than lower so 2 points are lost. 
power[3] and power[4] are greater than upper so 2 points are gained.
*/

import java.util.Scanner;

public class AP10_Fighter {

    static int n = 0, k = 0, low = 0, high = 0, points = 0;
    static int[] hours;

    static void point(int sum) {
        if (sum < low)
            points -= 1;
        else if (sum > high)
            points += 1;
    }

    static void solution1() {
        int sum = 0;

        for (int i = 0; i <= k - 1; i++) {
            sum += hours[i];
        }
        point(sum);

        for (int i = k; i < n; i++) {
            sum -= hours[i - k];
            sum += hours[i];
            point(sum);
        }
    }

    static void solution2() {
        int i = 0, j = 0, sum = 0;
        while (j < n) {
            if (j < i + k) {
                sum += hours[j];
            } else {
                point(sum);
                sum -= hours[i++];
                sum += hours[j];
            }
            j++;
        }
        point(sum);
    }
    /*
     * point(sum); is only called when j - i == k. finally j==n then j==i+k so
     * point(sum) not called for last window.
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        hours = new int[n];
        for (int i = 0; i < n; i++)
            hours[i] = sc.nextInt();

        k = sc.nextInt();
        low = sc.nextInt();
        high = sc.nextInt();

        solution2();

        System.out.println(points);
        sc.close();
    }
}
