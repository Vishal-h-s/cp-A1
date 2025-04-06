import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(",");
        char[] first = words[0].toCharArray();
        int count = 0, flag = 1;
        for (char character : first) {
            for (int idx = 1; idx < words.length; idx++) {
                if (words[idx].indexOf(character) == -1) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1)
                count++;
        }
        System.out.println(count);
        sc.close();
    }
}
