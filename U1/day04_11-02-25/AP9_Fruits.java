/*
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.

The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
You want to collect as much fruit as possible. 

However, the owner has some strict rules that you must follow:
You only have two baskets, and each basket can only hold a single type of fruit. 
There is no limit on the amount of fruit each basket can hold.

Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree)
while moving to the right. The picked fruits must fit in one of your baskets.

Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

Sample Input-1:
---------------
3
1 2 1

Sample Output-1: 
----------------
3

Explanation: We can pick from all 3 trees.

Sample Input-2:
--------------- 
4
0 1 2 2 

Sample Output-2: 
----------------
3

Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].

Sample Input-3: 
---------------- 
5
1 2 3 2 2

Sample Output-4: 
----------------
4

Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
 */

 class Solution {
    public int totalFruit(int[] fruits) {
        
        // // Brute Force, TC: O(n*n), SC: O(2)
        // // Will Throw TLE
        // int len = fruits.length;
        // int maxLen = 0;
        // Set<Integer> set = new HashSet<>();
        // for(int i = 0; i < len; i++){
        //     int elem = 0;
        //     for(int j = i; j < len; j++){
        //         if(!set.contains(fruits[j])){
        //             elem++;
        //         }
        //         if(elem > 2) break;
        //         maxLen = Math.max(maxLen, j - i + 1);
        //         set.add(fruits[j]);
        //     }
        //     set.clear();
        // }
        // return maxLen;


        // // Better, TC: O(n + n), SC: O(3)
        // int len = fruits.length;
        // int maxLen = 0;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for(int left = 0, right = 0; right < len; right++){
        //     map.put(fruits[right], 
        //     map.getOrDefault(fruits[right], 0) + 1);
        //     if(map.size() <= 2){
        //         maxLen = Math.max(maxLen,
        //             right - left + 1);
        //     }
        //     while(map.size() > 2){
        //         int occurence = map.get(fruits[left]);
        //         if(occurence == 1){
        //             map.remove(fruits[left]);
        //         }else{
        //             map.put(fruits[left], occurence - 1);
        //         } 
        //         left++;
        //     }
        // }
        // return maxLen;


        // // Optimal, TC: O(n), SC: O(3)
        // int len = fruits.length;
        // int maxLen = 0;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // for(int left = 0, right = 0; right < len; right++){
        //     map.put(fruits[right], 
        //     map.getOrDefault(fruits[right], 0) + 1);
        //     if(map.size() <= 2){
        //         maxLen = Math.max(maxLen,
        //             right - left + 1);
        //     }
        //     if(map.size() > 2){
        //         int occurence = map.get(fruits[left]);
        //         if(occurence == 1){
        //             map.remove(fruits[left]);
        //         }else{
        //             map.put(fruits[left], occurence - 1);
        //         } 
        //         left++;
        //     }
        // }
        // return maxLen;


        // // Optimal Improved, TC: O(n), SC: O(100001)
        // int len = fruits.length;
        // int maxLen = 0;
        // int hash[] = new int[100001];
        // int size = 0;
        // for(int left = 0, right = 0; right < len; right++){
        //     if(hash[fruits[right]] == 0){
        //         size++;
        //     }
        //     hash[fruits[right]]++;
        //     if(size <= 2){
        //         maxLen = Math.max(maxLen,
        //             right - left + 1);
        //     }
        //     if(size > 2){
        //         int occurence = hash[fruits[left]];
        //         if(occurence == 1){
        //             size--;
        //         }
        //         hash[fruits[left]]--; 
        //         left++;
        //     }
        // }
        // return maxLen;

        
        // Optimal Final, TC: O(n), SC: O(1)
        // Three pointer
        int type1 = fruits[0], type2 = -1;
        int n = fruits.length;
        int left = 0, right = 1, pre = 0;
        int ans = 0;

        while(right < n){
            int type = fruits[right];
            if(type != type1){
                if(type != type2  && type2 != -1){
                    ans = Math.max(ans, right - left);
                    left = pre;
                }
                type2 = type1;
                type1 = type;
                pre = right;
            }
            right++;
        }
        return Math.max(ans, right - left);
    }
}