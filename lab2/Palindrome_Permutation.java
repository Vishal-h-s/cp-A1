
import java.util.*;
public class Palindrome_Permutation {
    public static void main(String[]args){
        try (Scanner sc = new Scanner(System.in)) {
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
}