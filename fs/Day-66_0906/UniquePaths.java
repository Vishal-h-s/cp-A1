import java.util.Arrays;
import java.util.Scanner;

public class UniquePaths {
    
    static int uniquePaths(int m, int n) {
        int[] res = new int[n];
        Arrays.fill(res, 1);
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                res[j] += res[j - 1];
        return res[n - 1];
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int m = sc.nextInt(), n = sc.nextInt();
            System.out.println(uniquePaths(m, n));
        }
    }
}
