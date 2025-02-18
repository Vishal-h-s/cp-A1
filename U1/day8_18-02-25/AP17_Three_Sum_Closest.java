
/*
 * You are given an integer array nums of length n and an integer target. 
Your task is to find three integers in nums such that their sum is closest to the given target.

Return the sum of these three integers.

You may assume that each input would have exactly one solution.

Input Format:
-------------
Line-1: An integer n, the size of the array.
Line-2: n space-separated integers representing the elements of the array nums.
Line-3: A single integer target.

Output Format:
--------------
Line-1: Print a single integer, representing the sum of three integers closest to the target.

Sample Input-1:
---------------
4
-1 2 1 -4
1

Sample Output-1:
----------------
2

Sample Input-2:
---------------
3
0 0 0
1

Sample Output-2:
----------------
0


 */

import java.util.*;

public class AP17_Three_Sum_Closest {
    static int len, target, sum;
    static int[] nums, indices;
    // static int findClosest2(){

    // }
    static void findClosest3() {
        int minDifference = Integer.MAX_VALUE;
        Arrays.sort(nums);
        OUTER: for (int i = 0; i < len; i++) {
            int remainder = target - nums[i];
            int left = i, right = len - 1;
            // indices[0] = i;
            // && i!=left && i!=right this condition in outer loop will just 
            while (left < right ) {
                if(i==left) left++;
                if(i==right) right--;
                int currSum = nums[left] + nums[right];
                if (currSum == remainder) {
                    indices[0]=i;
                    indices[1] = left;
                    indices[2] = right;
                    break OUTER;
                }
                int currDiff = Math.abs(currSum - remainder);
                if (currDiff < minDifference) {
                    indices[0]=i;
                    indices[1] = left;
                    indices[2] = right;
                    minDifference = currDiff;
                }
                if (currSum < remainder) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    static void sumArray(){
        sum=0;
        for(int i: indices){
            sum+=nums[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = sc.nextInt();
        }
        target = sc.nextInt();
        indices = new int[3];
        findClosest3();
        sumArray();
        System.out.println(Arrays.toString(indices));
        System.out.println(sum);
        sc.close();
    }
}