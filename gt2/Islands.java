import java.util.*;
public class Islands {
    static int[][] matrix;
    static int nr, nc;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        nr=sc.nextInt();
        nc=sc.nextInt();
        matrix=new int[nr][nc];
        for(int i=0;i<nr;i++){
            for(int j=0;j<nc;j++){
                matrix[i][j]=sc.nextInt();
            }
        }

        
    }
}
