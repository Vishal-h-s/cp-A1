/*
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:
i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.

Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

Input Format:
-------------
Line-1: An integer n
Line-2: n space separated integers

Output Format:
--------------
Line-1: An integer

Sample Input-1:
---------------
10
100 -23 -23 404 100 23 23 23 3 404

Sample Output-1:
----------------
3

Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Sample Input-2:
---------------
1
7

Sample Output-2: 
----------------
0

Explanation: Start index is the last index. You do not need to jump.

Sample Input-3:
---------------
8
7 6 9 6 9 6 9 7

Sample Output-3:
----------------
1

Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 
 Constraints:
 ------------
1 <= arr.length <= 5 * 10^4
-10^8 <= arr[i] <= 10^8
 */

import java.util.*;
public class FS_AP1_Min_Jumps{
    static int len;
    static int[] nums;
    static HashMap<Integer,ArrayList<Integer>> repetingIndices;

    static void populateMap(){
        for(int idx=0;idx<len;idx++){
            if(repetingIndices.containsKey(nums[idx])){
                repetingIndices.get(nums[idx]).add(idx);
            }else{
                repetingIndices.put(nums[idx], new ArrayList<>(Arrays.asList(idx)));
            }
        }
    }

    static int getKey(int index){
        for(Map.Entry<Integer, ArrayList<Integer>> entry : repetingIndices.entrySet()){
            if(entry.getValue().contains(index)) return entry.getKey();
        }
        return -1;
    }

    static int solve(){

        int result=-1;


        return result;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        len=sc.nextInt();
        nums=new int[len];
        for(int idx=0;idx<len;idx++) nums[idx]=sc.nextInt();
        repetingIndices=new HashMap<>();
        populateMap();
        System.out.println(solve()+"\n"+repetingIndices);
        sc.close();
    }
 }