import java.util.*;

public class Fenwick {
    static int[] nums, bit;
    static int len;

    static void populateBit() {
        bit = Arrays.copyOf(nums, len);
        for (int idx = 1; idx <= len; idx++) {
            int dependant = idx + (idx & -idx);
            if (idx - 1 < len)
                bit[dependant - 1] += nums[idx - 1];
        }
    }

    static int sum(int index) {
        int result = 0;

        index++;
        while (index > 0) {
            result += bit[index - 1];
            index -= (index & -index);
        }
        return result;
    }

    static int inclusiveRangeSum(int start, int end) {
        return sum(end) - sum(start - 1);
        // because start must be included in our result
    }

    static int replace(int index, int newValue) {
        int oldValue = nums[index], diff = newValue - nums[index];
        nums[index] = newValue;

        index++;
        while (index > 0) {
            bit[index - 1] += diff;
            index += (index & -index);
        }
        return oldValue;
    }

    static void print(int[] array, int len) {
        for (int idx = 0; idx < len - 1; idx++) {
            System.out.print(array[idx] + " ");
        }
        System.out.println(array[len - 1]);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        len=sc.nextInt();
        nums=new int[len];
        for(int idx=0;idx<len;idx++){
            nums[idx]=sc.nextInt();
        }
        populateBit();
        print(nums, len);
        print(bit, len);
    }
}
