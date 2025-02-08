/*You are given an array consisting of N integers, and an integer, K. 
Your task is to determine the minimum element in subarrays of size K.

Sample Input1:
--------------
5
10 12 14 11 15
3

Sample Output1:
---------------
10 11 11

Sample Input2:
--------------
5
5 2 1 1 1
4

Sample Output2:
---------------
1 1

 */

import java.util.*;

public class AP1_SmallestElement {

    static int k = 0, len = 0, n = 0;
    static int[] nums;
    static int[] result;

    static void solution() {
        int[] window = new int[k];
        int front = 0, rear = -1, idx = 0;

        for (int i = 0; i < n; i++) {
            if (front <= rear && window[front] == i - k)
                front++;
            // the oldest element in the window. in a well-maintained sliding window (like
            // ours), we never accumulate multiple expired elements

            while (front <= rear && nums[window[rear]] >= nums[i])
                rear--;

            rear += 1;
            window[rear] = i;

            if (i >= k - 1)
                result[idx++] = nums[window[front]];
        }
    }

    static void print(int[] arr, int s) {
        for (int i = 0; i < s; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int i = 0;
            n = sc.nextInt();
            nums = new int[n];

            for (i = 0; i < n; i++)
                nums[i] = sc.nextInt();
            k = sc.nextInt();

            len = n - k + 1;
            result = new int[len];
            solution();

            print(nums, n);
            print(result, len);

            sc.close();
        }
    }
}