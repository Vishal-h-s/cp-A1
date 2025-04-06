/*
Mr.Ram is a sales manager of Dmart, for his analysis he has to monitor 
sales of Dmart every day. He need to send report of maximum sales 
of every K consecutive days from given N number of days sales.
Write a java program to do his task easy.

Input Format:
-------------
Line-1: Two space separated integers, N and K
Line-2: N space separated integers, sales[].

Output Format:
--------------
Print maximum sales of every K consecutive days


Sample Input 1:
---------------
9 3
1 2 3 1 4 5 2 3 6

Sample output 1:
----------------
3 3 4 5 5 5 6

Explanation: 
------------
Maximum of subarray {1, 2, 3} is 3
Maximum of subarray {2, 3, 1} is 3
Maximum of subarray {3, 1, 4} is 4
Maximum of subarray {1, 4, 5} is 5
Maximum of subarray {4, 5, 2} is 5
Maximum of subarray {5, 2, 3} is 5
Maximum of subarray {2, 3, 6} is 6
*/

import java.util.*;

public class SP4_MaxOfSubArrayofKWindow {

    static int len, windowSize;
    static int[] nums, result;
    
    static void solution() {
        Deque<Integer> window = new LinkedList<>();
        int index = 0;
        for (int idx = 0; idx < len; idx++) {
            if (!window.isEmpty() && window.peekFirst() == idx - windowSize ) {
                window.pollFirst();
            }
            while (!window.isEmpty() && nums[window.peekLast()] < nums[idx]) {
                window.pollLast();
            }
            window.offerLast(idx);
            if (idx >= windowSize - 1) {
                result[index++] = nums[window.peekFirst()];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        windowSize = sc.nextInt();
        nums = new int[len];
        result = new int[len - windowSize + 1];
        for (int idx = 0; idx < len; idx++) {
            nums[idx] = sc.nextInt();
        }
        solution();
        for(int num: result) System.out.print(num+" ");
        sc.close();
    }
}
