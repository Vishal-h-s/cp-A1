/*
A tenth standard student has been given a task to analyze a set of P subject marks.

Given an integer I, student needs to determine the I-th least sum among all possible contiguous subarrays of the marks list.

Input Format:
--------------
Line-1: Two space-separated integers, P (number of subjects) and I (the required position in the sorted sums list).
Line-2: P space-separated integers, representing the marks in each subject.

Output Format:
--------------
Line-1: Print a single integer, which is the I-th least sum among all possible contiguous subarrays.

Sample Input-1:
--------------
3 4
3 2 4

Sample output-1:
---------------
5

Explanation: 
------------
The subarrays of 3 2 4 are:
1st subarray: 3 the sum is 3
2nd subarray: 2 the sum is 2
3rd subarray: 4 the sum is 4
4th subarray: 3,2 the sum is 5
5th subarray: 2,4 the sum is 6
6th subarray: 3,2,4 the sum is 9

The 4th smallest is 5

Sample Input-2:
---------------
4 7
2 2 4 4

Sample output-2:
----------------
8

Explanation: 
------------
The subarrays of 2 2 4 4 are

1st subarray: 2 the sum is 2
2nd subarray: 2 the sum is 2
3rd subarray: 4 the sum is 4
4th subarray: 4 the sum is 4
5th subarray: 2,2 the sum is 4
6th subarray: 2,4 the sum is 6
7th subarray: 4,4 the sum is 8
8th subarray: 2,2,4 the sum is 8
9th subarray: 2,4,4 the sum is 10
10th subarray: 2,2,4,4 the sum is 12

The 7th smallest is 8
*/

import java.util.*;

public class SP3_KthSmallestSubarraySum {
    // static int len, position, result;
    // static int[] nums;
    // static List<Integer> window = new ArrayList<>();

    // static int getPosition(int sum) {
    //     if (window.isEmpty())
    //         return 0;
    //     int left = 0, right = window.size() - 1;
    //     while (left <= right) {
    //         int mid = left + (right - left) / 2;
    //         if (sum < window.get(mid)) {
    //             right = mid - 1;
    //         } else
    //             left = mid + 1;
    //     }
    //     return left;
    // }

    // static void solution1() {
    //     for (int idx = 0; idx < len; idx++) {
    //         int sum = 0;
    //         for (int jdx = idx; jdx < len; jdx++) {
    //             sum += nums[jdx];
    //             int size = window.size();
    //             // if (size == 0 || sum >= window.getLast())
    //             // window.add(sum);
    //             // else {
    //             int pos = getPosition(sum);
    //             window.add(pos, sum);
    //             size++;
    //             // }

    //             if (size > position)
    //                 window.remove(size - 1);
    //             System.out.println(window);
    //         }
    //     }
    //     result = window.get(position - 1);
    // }

    static int solution(int[]nums, int position) {
        int left = Integer.MAX_VALUE, right = 0,  len=nums.length;
        for (int idx=0;idx<len;idx++) {
            int num=nums[idx];
            System.out.println("110 num "+num);
            left = Math.min(left, num);
            right += num;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.println("116 left "+left+" right "+right+" mid "+mid);
            int count = countSubarraysWithSumAtMost(nums, mid);
            if (count >= position)
                right = mid;
            // even if it matches we still try a smaller sum
            else {
                // if a smaller sum wont be feasible we will anyway end up at the same value
                // because we set right =mid and not mid-1 so that mid is still an option
                // but this intuition seems to be wrong and we dont return mid but left instead
                // if smaller sum is not feasible we are already in next iteration and mid value
                // has changes
                // say, 1 and 5 were previous values but we set right to 3 now it is not
                // feasible so we say left = 3+1 = 4
                // 4 and 5. set right=3 assumnig not feasible left = 4+1 = 5 exit loop
                // does that mean we can return either left or right
                left = mid + 1;
            }
        }
        System.out.println(left == right);
        return left;
    }

    static int countSubarraysWithSumAtMost(int[] nums, int target) {
        int count = 0, sum = 0;
        int left = 0, right = 0, len=nums.length;
        for (right = 0; right < len; right++) {
            sum += nums[right];
            while (sum > target) {
                System.out.println("144 nums[left] "+nums[left]);
                sum -= nums[left++];
                System.out.println("146 target "+target+" sum "+sum);
            }
            count += right - left + 1;
            System.out.println("149 count "+count);

        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt(),
        position = sc.nextInt();
        int[] nums = new int[len];
        for (int idx = 0; idx < len; idx++) {
            nums[idx] = sc.nextInt();
        }
        int result= solution(nums, position);
        System.out.println(result);
        sc.close();
    }
}
