/*
 * Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
spectacular dinner consisting of numDishes unique dishes, labeled from 
0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
only be prepared after completing others. You’re given a list called dependecies, 
where each element dependencies[i] = [Xi, Yi] means that you must finish 
preparing dish Yi before starting dish Xi.

For instance, the pair [2, 1] implies that to create dish 2, 
dish 1 must be prepared first.

Return the ordering of dishes that a chef should take to finish all dishes.
	- the result set should follow the given order of conditions.
	- If it is impossible to complete all dishes, return an empty set.


Input Format:
-------------
Line-1: An integer numDishes, number of Dishes.
Line-2: An integer m, number of dependencies.
Next m lines: Two space separated integers, Xi and Yi.

Output Format:
--------------
Return a list of integers, the ordering of dishes that a chef should finish.

Sample Input-1:
---------------
4
3
1 2
3 0
0 1

Sample Output-1:
----------------
[2, 1, 0, 3]

Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


Sample Input-2:
---------------
2
2
1 0
0 1

Sample Output-1:
----------------
[]

Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
on dish 1. This circular dependency makes it impossible to prepare all dishes.

Constraints:
------------
- 1 <= numDishes <= 2000  
- 0 <= m <= 5000  
- dependencies[i].length == 2  
- 0 <= Xi, Yi < numDishes  
- All the dependency pairs are unique.

 */

public class FS_AP11_Master_Chef {
    import java.util.*;

public class Solution {
    public static List<Integer> findOrder(int numDishes, int[][] dependencies) {
        // Step 1: Create an adjacency list and in-degree array
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[numDishes];
        for (int i = 0; i < numDishes; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Populate the adjacency list and in-degree array
        for (int[] dependency : dependencies) {
            int dish = dependency[0];
            int prerequisite = dependency[1];
            adjList.get(prerequisite).add(dish);
            inDegree[dish]++;
        }

        // Step 3: Initialize a queue with all nodes having in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numDishes; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 4: Perform topological sorting
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            // Reduce the in-degree of neighbors
            for (int neighbor : adjList.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 5: Check if topological sorting is possible
        if (order.size() != numDishes) {
            return new ArrayList<>(); // Return an empty list if there's a cycle
        }

        return order;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of dishes and dependencies
        int numDishes = sc.nextInt();
        int m = sc.nextInt();
        int[][] dependencies = new int[m][2];
        for (int i = 0; i < m; i++) {
            dependencies[i][0] = sc.nextInt();
            dependencies[i][1] = sc.nextInt();
        }

        // Find the order of dishes
        List<Integer> result = findOrder(numDishes, dependencies);
        System.out.println(result);

        sc.close();
    }
}
}
