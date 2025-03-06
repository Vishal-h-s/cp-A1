import java.util.*;
public class FS_AP9_MinSteps {
    static boolean isEven(int n){
        return (n&1)==1 ? false : true;
    }
    
    static int minSteps(int num){
        int minsteps;
        if(num==1) return 0;
        if(isEven(num)) minsteps = 1+minSteps(num>>1);
        else{
            minsteps = 1+Math.min(minSteps(num+1), minSteps(num-1));
        }
        return minsteps;
    }
    
    static int method2(int num){
        int count=0;
        while(num>1){
            if(isEven(num)){
                num>>=1;
                
            }
            else{
                if(isEven(num+1)) num++;
                else num--;
            }
            count++;
        }
        return count;
    }
    
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        System.out.println(minSteps(num));
    }
}