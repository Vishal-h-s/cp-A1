 
/* You are given an integer array nums of length n and an integer target.
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
        OUTER: for (int idx = 0; idx < len; idx++) {
            int remainder = target - nums[idx];
            int left = idx, right = len - 1;
            // indices[0] = i; this shouldnt be done here
            //becoz it updates i even when its not valid and previously assigned valid value is lost
            // && i!=left && i!=right this condition in outer loop will just terminate loop in advance
            // instead we will skip only that index 
            while (left < right ) {
                if(idx==left) left++;
                if(idx==right) right--;
                int currSum = nums[left] + nums[right];
                if (currSum == remainder) {
                    // this condition can be removed but it optimizes (using break)
                    indices[0]=idx;
                    indices[1] = left;
                    indices[2] = right;
                    break OUTER;
                }
                int currDiff = Math.abs(currSum - remainder);
                if (currDiff < minDifference) {
                    indices[0]=idx;
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

