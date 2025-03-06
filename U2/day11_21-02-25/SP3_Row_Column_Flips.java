import java.util.*;

public class SP3_Row_Column_Flips {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt(), cols = sc.nextInt();
        int[][] mat = new int[rows][cols];

        for (int idx = 0; idx < rows; idx++) {
            for (int jdx = 0; jdx < cols; jdx++) {
                mat[idx][jdx] = sc.nextInt();
            }
        }

        boolean result = true;
        for (int idx = 0; idx < rows; idx++) {
            int xor = mat[0][0] ^ mat[idx][0];
            for (int jdx = 0; jdx < cols; jdx++) {
                if (xor != (mat[0][jdx] ^ mat[idx][jdx]))
                    result = false;
            }
        }

        System.out.println(result);
        sc.close();
    }
}