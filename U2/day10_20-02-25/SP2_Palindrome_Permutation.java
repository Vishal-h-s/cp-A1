/*
 * AlphaCipher is a string formed from another string by rearranging its letters

You are given a string S.
Your task is to check, can any one of the AlphaCipher is a palindrome or not.

Input Format:
-------------
A string S

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
carrace

Sample Output-1:
----------------
true


Sample Input-2:
---------------
code

Sample Output-2:
----------------
false
 */
import java.util.*;
public class SP2_Palindrome_Permutation {
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        char[] letters=sc.nextLine().toCharArray();
        int res=0;
        boolean isPalin=false;
        for(char letter : letters){
            res ^= (1 << (letter - 'a'));
        }
        if(res==0 || (res & (res - 1))==0) isPalin=true;
        System.out.println(isPalin);
        sc.close();
    }
}
