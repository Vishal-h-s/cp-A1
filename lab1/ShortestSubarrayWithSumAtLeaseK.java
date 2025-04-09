/*
 * Write a JAVA Program to find shortest sub array with sum at least K 
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K. If 
there is no non-empty subarray with sum at least K, return -1. 
Example 1: 
Input: A = [1], K = 1 Output: 1 
Example 2: 
Input: A = [1,2], K = 4 Output: -1 
Example 3: 
Input: A = [2,-1,2], K = 3 Output: 3 
Note: 1 <= A.length <= 50000 -10 ^ 5 <= A[i] <= 10 ^ 5 1 <= K <= 10 ^ 9
 */

import java.util.*;

public class ShortestSubarrayWithSumAtLeaseK {

    static int shortestSubarray(int[] A, int K) {
        int n = A.length;
        long[] prefixSum = new long[n + 1];
        Arrays.fill(prefixSum, 0);
        for (int i = 0; i < n; i++)
            prefixSum[i + 1] = prefixSum[i] + A[i];

        Deque<Integer> deque = new LinkedList<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // remove indices from deque if sum is at least K
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= K)
                minLength = Math.min(minLength, i - deque.pollFirst());

            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()])
                deque.pollLast();

            deque.offer(i);

        }
        return minLength==Integer.MAX_VALUE?-1:minLength;
    }

    public static void main(String[] args) {
        int[] a1 = { 1 }, a2 = { 1, 2 }, a3 = { 2, -1, 2 };
        int k1 = 1, k2 = 4, k3 = 3;
        System.out.println(shortestSubarray(a1, k1));
        System.out.println(shortestSubarray(a2, k2));
        System.out.println(shortestSubarray(a3, k3));
    }
}