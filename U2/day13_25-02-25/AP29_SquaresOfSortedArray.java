
/*
* Given an integer array nums sorted in non-decreasing order, 
return an array of the squares of each number sorted in non-decreasing order.

Input Format:
-------------
Line-1: An integer N
Line-2: N space seperated integers

Output Format:
--------------
Line-1: A list of integers

Sample Input-1:
---------------
5
-4 -1 0 3 10

Sample Output-1: 
----------------
[0, 1, 9, 16, 100]

*/
import java.util.*;

public class AP29_SquaresOfSortedArray {
    static int[] nums;
    static int len;
    static int[] res;

    static int firstNonNegative() {
        int left = 0, right = len - 1, mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == 0)
                return mid;
            else if (nums[mid] > 0) {
                if (nums[mid - 1] < 0)
                    return mid;
                else
                    right = mid - 1;
            } else
                left = mid + 1;
        }
        return mid;
    }

    static void method1() {
        int idx = 0;
        int right = firstNonNegative();
        int left = right - 1;
        while (left < right && left >= 0 && right < len && idx < len) {
            int lefty = Math.abs(nums[left]), righty = Math.abs(nums[right]);
            if (lefty < righty) {
                res[idx++] = (int) Math.pow(lefty, 2);
                left--;
            } else {
                res[idx++] = (int) Math.pow(righty, 2);
                right++;
            }
            // else {//equal
            // res[idx++] = (int) Math.pow(lefty, 2);
            // res[idx++] = (int) Math.pow(righty, 2);
            // left--;
            // right++;
            // }
            //if we do this then we cant compare this number with next number(right or left whatever dosent matter)
        }
        //since we included (left >= 0 && right < len) we need to do the below ops
        while (left >= 0) {
            res[idx++] = (int) Math.pow(nums[left--], 2);
        }
        while (right < len) {
            res[idx++] = (int) Math.pow(nums[right++], 2);
        }
    }

    static void method2() {
        int left = 0, right = len - 1, idx = len - 1;
        while (left <= right && idx >= 0) {
            int lefty = Math.abs(nums[left]), righty = Math.abs(nums[right]);
            if (lefty < righty) {
                res[idx--] = (int) Math.pow(righty, 2);
                right--;
            } else {
                res[idx--] = (int) Math.pow(lefty, 2);
                left++;
            }
            // else {//equal
            // res[idx--] = (int) Math.pow(lefty, 2);
            // res[idx--] = (int) Math.pow(righty, 2);
            // right--;
            // left++;
            // }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        nums = new int[len];
        for (int idx = 0; idx < len; idx++)
            nums[idx] = sc.nextInt();
        res = new int[len];

        method1();
        // method2();
        System.out.println(Arrays.toString(res));
        sc.close();
    }
}
