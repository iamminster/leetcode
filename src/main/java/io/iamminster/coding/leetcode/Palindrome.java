package io.iamminster.coding.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

    // 121 --> true
    // -121 --> false
    // 10 --> false
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        List<Integer> forward = new ArrayList<Integer>();
        List<Integer> backward = new ArrayList<Integer>();

        while (x != 0) {
            backward.add(x % 10);
            x = x / 10;
        }
        for (int i = backward.size() - 1; i >= 0; --i) {
            forward.add(backward.get(i));
        }

        for (int i = 0; i < forward.size(); ++i) {
            if (forward.get(i) != backward.get(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Palindrome pd = new Palindrome();
        System.out.println(pd.isPalindrome(10));
    }
}
