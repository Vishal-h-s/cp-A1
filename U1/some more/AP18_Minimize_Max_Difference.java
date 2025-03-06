/*
 * You are given three sorted integer arrays A[], B[], and C[]. 

Your task is to find one element from each array (A[i], B[j], and C[k]) 
such that the following expression is minimized:
    
   x =  max(∣A[i]−B[j]∣,∣B[j]−C[k]∣,∣C[k]−A[i]∣)
where |x| denotes the absolute value of x.

You need to return the three selected elements A[i], B[j], and C[k] that result in the minimum possible value of the above expression.

Input Format:
-------------
Line-1: An integer n, the size of array A, followed by n space-separated integers representing array A.
Line-2: An integer m, the size of array B, followed by m space-separated integers representing array B.
Line-3: An integer p, the size of array C, followed by p space-separated integers representing array C.

Output Format:
--------------
Line-1: Print three space-separated integers A[i] B[j] C[k] that minimize the given expression.


Sample Input-1:
---------------
3
1 4 10
3
2 15 20
2
10 12

Sample Output-1:
----------------
10 15 10

Sample Input-2:
---------------
24 22 23

Sample Output-1:
----------------
10 15 10

Sample Input-2:
---------------
3
0 0 0
1

Sample Output-2:
----------------
0
 */