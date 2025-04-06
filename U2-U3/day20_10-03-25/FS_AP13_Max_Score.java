/*
* You are given an array of positive integers. 
Your task is to find a contiguous subarray where all elements are unique 
and return the maximum possible sum that can be obtained by erasing exactly one such subarray.

A subarray is defined as a contiguous sequence of elements within the given array.

Input Format:
-------------
Line-1: An integer N, representing the number of elements in the array.
Line-2: Space-separated integers, representing the elements of the array.

Output Format:
--------------
Line-1: A single integer, representing the maximum sum of a contiguous subarray with all unique elements.

Sample Input-1:
---------------
5  
4 2 4 5 6  

Sample Output-1:
----------------
17

Explanation:
-------------
The longest unique subarray is [2, 4, 5, 6] with a sum of 2 + 4 + 5 + 6 = 17.
This is the maximum possible sum that can be obtained.


Sample Input-2:
---------------
6  
1 2 3 1 2 3  

Sample Output-2:
----------------
6


Explanation:
-------------
The longest unique subarray is [1, 2, 3] with a sum of 1 + 2 + 3 = 6.
This sum cannot be improved by choosing another unique subarray.
*/

import java.util.*;

public class FS_AP13_Max_Score {
    static int solution(int[] nums, int len) {
        int left = 0, right = 0, currentSum = 0, maxSum = 0;
        Set<Integer> uniques = new HashSet<>();
        for (right = 0; right < len; right++) {
            while (uniques.contains(nums[right])) {
                currentSum -= nums[left];
                uniques.remove(nums[left++]);
            }
            uniques.add(nums[right]);
            currentSum += nums[right];
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    static int solution2(int[] nums, int len) {
        int left = 0, right = 0, currSum = 0, maxSum = 0;
        HashMap<Integer, Integer> previousIndices = new HashMap<>();
        for (right = 0; right < len; right++) {
            if (previousIndices.containsKey(nums[right])) {
                int previousIndex=previousIndices.get(nums[right]);
                while (left <= previousIndex) {
                    currSum -= nums[left++];
                }
            }
            previousIndices.put(nums[right], right);
            currSum += nums[right];
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] nums = new int[len];
        for (int idx = 0; idx < len; idx++)
            nums[idx] = sc.nextInt();
        int result = solution2(nums, len);
        System.out.println(result);
        sc.close();
    }
}
