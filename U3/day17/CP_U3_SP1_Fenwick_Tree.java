
/*
 * Malika taught a new fun time program practice for Engineering Students.
As a part of this she has given set of N numbers, and asked the students 
to perform the operations listed below:
1. sumRange(start, end) - return the sum of numbers between the indices start and end, both are inclusive.
2. update(ind, val) - update the value at the index 'ind' to 'val'.

Your task is to solve this problem using Fenwick Tree concept.

Input Format:
-------------
Line-1: Two integers N and Q, size of the array(set of numbers) and query count.
Line-2: N space separated integers.
next Q lines: Three integers option, start/ind and end/val.

Output Format:
--------------
An integer result, for every sumRange query.


Sample Input-1:
---------------
8 5
1 2 13 4 25 16 17 8
1 2 6		//sumRange
1 0 7		//sumRange
2 2 18	//update
2 4 17	//update
1 2 7		//sumRange

Sample Output-1:
----------------
75
86
80



Sample Input-2:
---------------
8 5
1 2 13 4 25 16 17 8
1 2 6		
1 0 7		
2 2 18	
2 4 17	
1 0 7

Sample Output-2:
----------------
75
86
83

 */
import java.util.Arrays;
import java.util.Scanner;

public class CP_U3_SP1_Fenwick_Tree {
    static int len;
    static int[] nums, bit;

    static void populateBit() {
        Arrays.fill(bit, 0);
        for (int idx = 0; idx < len; idx++) {
            int jdx = idx + 1;
            while (jdx <= len) {
                bit[jdx - 1] += nums[idx];
                jdx += (jdx & -jdx);
            }
        }
    }

    static void populateBit2() {
        bit = Arrays.copyOf(nums, len);

        for (int idx = 1; idx <= len; idx++) {
            int dependent = idx + (idx & -idx);
            if (dependent - 1 < len) {
                bit[dependent - 1] += bit[idx - 1];
            }
        }
    }

    static void print(int[] array, int len) {
        for (int idx = 0; idx < len - 1; idx++) {
            System.out.print(array[idx] + " ");
        }
        System.out.println(array[len - 1]);
    }

    static int inclusiveRangeSum(int start, int end) {
        return sum(end) - sum(start - 1);
        //because start must be included in our result
    }

    static int sum(int index) {
        int result = 0;
        index++;
        while (index > 0) {
            result += bit[index - 1];
            index -= (index & -index);
        }
        return result;
    }

    static void replace(int index, int newValue) {
        int diff = newValue - nums[index];
        nums[index] = newValue;
        int jdx = index + 1;
        while (jdx <= len) {
            bit[jdx - 1] += diff;
            jdx += (jdx & -jdx);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        int queries = sc.nextInt();
        nums = new int[len];
        bit = new int[len];
        for (int idx = 0; idx < len; idx++)
            nums[idx] = sc.nextInt();
        populateBit();
        // populateBit2();
        print(bit, len);
        int result;
        while (queries-- > 0) {
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    int start = sc.nextInt();
                    int end = sc.nextInt();
                    result = inclusiveRangeSum(start, end);
                    System.out.println(result);
                    break;
                case 2:
                    int index = sc.nextInt();
                    int newValue = sc.nextInt();
                    replace(index, newValue);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        sc.close();
    }
}