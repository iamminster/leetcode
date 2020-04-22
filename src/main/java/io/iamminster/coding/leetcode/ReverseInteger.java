package io.iamminster.coding.leetcode;

public class ReverseInteger {
    // 123 --> 321
    // -123 --> -321
    // 120 --> 21
    // -2^31 ~ 2^31 - 1
    // -2^31 --> 0
    public int reverse(int x) {
        if (x == 0x80000000)
            return 0;
        if (x == 0x7fffffff)
            return (0x80000000 + 1);
        if (x < 0) {
            x = -x;
            return -reversePosInt(x);
        }
        return reversePosInt(x);
    }

    private int reversePosInt(int x) {
        int result = 0;
        while (x != 0) {
            long flowNum = (long) result * 10;
            if (flowNum < Integer.MIN_VALUE || flowNum > Integer.MAX_VALUE) {
                return 0;
            }
            result = result * 10 + (x % 10);
            x = x / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(1534236469));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1534236469);
        int x = 1534236469;
        System.out.println((long)x * 10);


    }
}
