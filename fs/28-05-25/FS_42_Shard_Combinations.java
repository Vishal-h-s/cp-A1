/*
 * You are given a crystal with an energy level n. Your goal is to discover all the different ways this crystal could have been created by combining smaller shards.

Each combination must:
- Use only shards with energy values between 2 and n - 1.
- Be represented as a list of shard values whose product equals n.
- Use any number of shards (minimum 2), and the order is ascending order.

Your task is to return all unique shard combinations that can multiply together to recreate the original crystal.

Input Format:
-------------
Line-1: An integer

Output Format:
--------------
Line-1: List of all unique shard combinations

Sample Input-1:
---------------
28

Sample Output-1:
----------------
[[2, 14], [2, 2, 7], [4, 7]]

Sample Input-2:
---------------
23

Sample Output-2:
----------------
[]


Constraints:
- 1 <= n <= 10^4
- Only shards with energy between 2 and n - 1 can be used.

 */

import java.util.*;

public class FS_42_Shard_Combinations {
    public static List<List<Integer>> shradCombinations(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 1)
            return result;
        findCombinations(n, 2, new ArrayList<>(), result);
        return result;
    }

    private static void findCombinations(int n, int start, List<Integer> current, List<List<Integer>> result) {
        if (n == 1) {
            if (current.size() > 1)
                result.add(new ArrayList<>());
            return;
        }

        for(int i=start, i<=n;i++){
            if(n%i==0){
                current.add(i);
                findCombinations(n/i, i, current, result);      
                current.remove(current.size()-1);
                
            }
        }

    }
}