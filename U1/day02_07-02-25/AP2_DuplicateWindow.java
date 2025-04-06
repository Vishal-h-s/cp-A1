/*Given an integer array of nums and an integer window size X, 
return true if there are duplicate values (nums[i]== nums[j])in that Window(X)
X size is always <= abs(i - j) where i and j are two distinct indices of array.

Input Format:
-------------
Line-1: An integer N, Array Size
Line-2: Space separated integers, array elements
Line-3: An integer X, window size

Output Format:
--------------
Line-1: Booelan value, true/false


Sample Input-1:
---------------
4
1 2 3 1  
3 

Sample Output-1: 
----------------
false

Sample Input-2:
---------------
6
1 2 3 3 2 3
2

Sample Output-2: 
----------------
true */

import java.util.*;

public class AP2_DuplicateWindow {
    static int n = 0, x = 0, len = 0;
    static int[] nums;
    static boolean result = false;

    static void solution() {
        // Map<Integer, Integer> freq = new HashMap<>();
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < n; i++) {
            // int iter = 0;
            
            if (i >= x) {
                // for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                //     if (entry.getValue() > 1) {
                //         result = true;
                //         break;
                //     }
                //     iter++;
                // }
                // System.out.println(i + " " + iter);
                int prev = nums[i - x];
                set.remove(prev);
            }
            int curr = nums[i];
            if(set.contains(curr)) {
                result=true;
                break;
            }
            set.add(curr);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        x = sc.nextInt();

        solution();

        System.out.println(result);
        sc.close();
    }
}