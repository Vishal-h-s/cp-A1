/*
 * You are a renowned Alchemist exploring a mystical forest teeming with magical plants. 
Each plant you encounter has a unique Essence Power represented by an integer in 
the array plants of length n.

By collecting essences from consecutive plants, you can brew powerful Elixirs. 
The potency of an elixir is determined by the sum of the essence powers of the plants used. 
Due to the complexity of brewing, you can only create elixirs using sequences of 
plants that are at least m in length.

Your objective is to find the kth smallest elixir potency that can be brewed 
from these sequences.

Example 1:
Input: n=3 plants = [2, 1, 3], k = 2, m = 2
Output: 4

Explanation:
Possible elixirs (sequences of length ≥ 2):
- [2, 1]: Potency = 2 + 1 = 3
- [1, 3]: Potency = 1 + 3 = 4
- [2, 1, 3]: Potency = 2 + 1 + 3 = 6
Ordered elixir potencies: 3, 4, 6
The 2nd smallest elixir potency is 4.

Input Format:
-------------
Line-1: 3 space separated integers, n, k, m
Line-2: n space separated integers, movieRatings[].

Output Format:
-------------
An integer, The kth smallest elixir potency


Sample Input:
4 3 2
3 -3 5 2

Sample output:
4

Explanation:
Possible elixirs (sequences of length ≥ 2):
- [3, -3]: Potency = 3 + -3 = 0
- [-3, 5]: Potency = -3 + 5 = 2
- [5, 2]: Potency = 5 + 2 = 7
- [3, -3, 5]: Potency = 3 + -3 + 5 = 5
- [-3, 5, 2]: Potency = -3 + 5 + 2 = 4
- [3, -3, 5, 2]: Potency = 3 + -3 + 5 + 2 =7

Ordered elixir potencies: 0, 2, 4, 5, 7, 7
The 3rd smallest elixir potency is 4.
 */
public class FS_AP13_ElixirPotency {
    
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
    import java.util.*;

    public class FS_AP13_ElixirPotency {
        public static int findKthSmallestElixir(int n, int k, int m, int[] plants) {
            // Min-heap to store all valid elixir potencies
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
            // Generate all subarrays of length >= m
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += plants[j];
                    if (j - i + 1 >= m) { // Only consider subarrays of length >= m
                        minHeap.offer(sum);
                    }
                }
            }
    
            // Extract the k smallest elements from the heap
            int result = 0;
            for (int i = 0; i < k; i++) {
                if (!minHeap.isEmpty()) {
                    result = minHeap.poll();
                } else {
                    throw new IllegalArgumentException("Not enough elixirs to find the kth smallest.");
                }
            }
    
            return result;
        }
    
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
    
            // Input: n, k, m
            int n = sc.nextInt();
            int k = sc.nextInt();
            int m = sc.nextInt();
    
            // Input: plants array
            int[] plants = new int[n];
            for (int i = 0; i < n; i++) {
                plants[i] = sc.nextInt();
            }
    
            // Find and print the kth smallest elixir potency
            System.out.println(findKthSmallestElixir(n, k, m, plants));
    
            sc.close();
        }
    }