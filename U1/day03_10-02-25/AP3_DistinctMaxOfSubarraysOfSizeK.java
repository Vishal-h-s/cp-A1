/*
You are given an array of N heights. 
Your task is to find the maximum sum of any contiguous subarray of length L, 
such that all the elements in the subarray are distinct.

If no such subarray exists, return 0.

Constraints:
------------
-> A subarray is a continuous sequence of elements from the array.
-> The length of the subarray must be exactly L.
-> All elements in the subarray must be unique.

Input Format:
-------------
Line-1: Two space-separated integers N (size of the array) and L (length of the subarray).
Line-2: N space-separated integers, representing the heights array.

Output Format:
--------------
Print a single integer, representing the maximum sum of any valid subarray.
If no valid subarray exists, print 0.

Sample Input-1:
---------------
7 3
7 7 7 1 5 4 2

Sample Output-1:
----------------
13

Explanation:
------------
Valid subarrays of length 3:
[7,7,7] ❌ (invalid, repeated 7)
[7,7,1] ❌ (invalid, repeated 7)
[7,1,5] ✅ (valid, sum = 13)
[1,5,4] ✅ (valid, sum = 10)
[5,4,2] ✅ (valid, sum = 11)
The maximum valid sum is 13.

Sample Input-2:
---------------
3 3
7 7 7

Sample Output-2:
----------------
0

Explanation:
-------------
Only one subarray of length 3 exists: [7,7,7], but it contains repeated elements.
Since no valid subarray exists, return 0.
*/

import java.util.*;

public class AP3_DistinctMaxOfSubarraysOfSizeK {
    static int len, windowSize, result;
    static int[] nums;

    static void solution() {
        Set<Integer> uniques = new HashSet<>();
        int curSum = 0, start = 0;
        result = 0;
        for (int end = 0; end < len; end++) {
            while (uniques.contains(nums[end])) {
                uniques.remove(nums[start]);
                curSum -= nums[start];
                start++;
            }

            uniques.add(nums[end]);
            curSum += nums[end];

            if (end - start + 1 == windowSize) {
                result = Math.max(result, curSum);
                uniques.remove(nums[start]);
                curSum -= nums[start];
                start++;
            }
        }
    }

    static void solution2() {
        // replace set features with a freq array
        int cursum = 0;
        int start = 0;

        int[] freq = new int[100001];
        for (int end = 0; end < nums.length; end++) {

            cursum += nums[end];
            freq[nums[end]]++;

            while (freq[nums[end]] > 1) {
                freq[nums[start]]--;
                cursum -= nums[start];
                start++;
            }

            if (end - start + 1 == windowSize) {
                result = Math.max(result, cursum);
                freq[nums[start]]--;
                cursum -= nums[start];
                start++;
            }
        }

    }

    static void solution3() {

        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, nums[i - 1]);
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
        }
        int[] index = new int[max + 1];
        for (int e = 1, s = 0; e <= nums.length; e++) {
            s = Math.max(s, Math.max(e - windowSize, index[nums[e - 1]]));
            if (e - s == windowSize) {
                result = Math.max(result, prefixSum[e] - prefixSum[s]);
            }
            index[nums[e - 1]] = e;
        }
    }

    static void solution4() {

        int i = 0, j = 0;
        int sum = 0;
        int[] freq = new int[100001]; // Frequency array
        int dupCount = 0; // Count of duplicate elements in the window

        while (j < nums.length) {
            sum += nums[j];
            freq[nums[j]]++;

            if (freq[nums[j]] == 2) { // A duplicate appears
                dupCount++;
            }

            // If there is a duplicate, shrink the window
            while (dupCount > 0) {
                freq[nums[i]]--;
                sum -= nums[i];

                if (freq[nums[i]] == 1) { // A duplicate is removed
                    dupCount--;
                }
                i++;
            }

            // Check if window size is exactly k
            if (j - i + 1 == windowSize) {
                result = Math.max(result, sum);

                // Remove the leftmost element to slide the window
                freq[nums[i]]--;
                sum -= nums[i];

                if (freq[nums[i]] == 1) { // A duplicate is removed
                    dupCount--;
                }
                i++;
            }

            j++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        windowSize = sc.nextInt();
        nums = new int[len];
        // result=new int[len-windowSize+1];

        for (int idx = 0; idx < len; idx++) {
            nums[idx] = sc.nextInt();
        }

        solution();
        // solution2();
        // solution3();
        // solution4();
        System.out.print(result);
        sc.close();
    }
}
