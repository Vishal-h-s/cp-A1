import java.util.*;

public class SP4_Encode_Number {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int decimal = (num + 1);
        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.append(String.valueOf(decimal & 1));
            decimal >>= 1;
        }
        binary = binary.reverse();
        System.out.println(binary.toString().substring(1));
        sc.close();
    }
}