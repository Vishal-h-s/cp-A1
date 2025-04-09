/*
 * Write a JAVA Program to find Subarrays with K Different integers 
Given an array  A of positive integers,  call a  (contiguous, not necessarily  distinct) subarray of A 
good if the number of different integers in that subarray is exactly K. (For example, [1,2,3,1,2]  has 
3 different integers: 1, 2, and 3.) Return the number of good subarrays of A. 
Example 1: 
Input: A = [1,2,1,2,3], K = 2 
Output: 7 
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2] 
Example 2: 
Input: A = [1,2,1,3,4], K = 3 
Output: 3 
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], 
[1,3,4]. Note: 
1 <= A.length <= 20000 
1 <= A[i] <= A.length 1 <= K <= A.length
 */

import java.util.*;
public class SubarraysWithKDifferentIntegers{

    static int subArraysWithKDistinct(int[]A, int K){
        return atMostKDistinct(A,K)-atMostKDistinct(A,K-1);
    }
    static int atMostKDistinct(int[]A,int K){
        Map<Integer,Integer> freq=new HashMap<>();
        int start=0, count=0;
        for(int end=0; end<A.length;end++){
            freq.put(A[end],freq.getOrDefault(A[end], 0)+1);

            while(freq.size()>K){
                freq.put(A[start],freq.get(A[start])-1);
                if(freq.get(A[start])==0) freq.remove(A[start]);
                start++;
            }
            count+=end-start+1;
        }
        return count;
    }
    public static void main(String[] args) {
        int[] A1={1,2,1,2,3}, A2={1,2,1,3,4};
        int K1=2,K2=3;
        System.out.println(subArraysWithKDistinct(A1, K1));
        System.out.println(subArraysWithKDistinct(A2, K2));
    }
}