package io.iamminster.coding.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // validate nums
        // validate target

        // logic
        Map<Integer, int[]> m = new HashMap<Integer, int[]>();
        int arrLength = nums.length;
        for (int i = 0; i < arrLength - 1; ++i) {
            for (int j = i + 1; j < arrLength; ++j) {
                int sum = nums[i] + nums[j];
                int[] pair = new int[] {i, j};
                m.put(sum, pair);
            }
        }
        return m.get(target);
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum solution = new TwoSum();
        int[] pair = solution.twoSum(nums, target);
        solution.printPair(pair);

    }

    public void printPair(int[] pair) {
        System.out.println(pair[0] + ", " + pair[1]);
    }
}
