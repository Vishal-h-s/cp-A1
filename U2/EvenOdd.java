package U2;

public class EvenOdd {
    boolean isEven(int num) {
        return (num & 1) == 0;
    }

    public static void main(String[] args) {

    }

    int countSetBits(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1)
                count++;
            num >>= 1;
        }
        return count;
    }
}
