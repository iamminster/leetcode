package io.iamminster.coding.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // validate nums
        // validate target

        // logic
        return sol4(nums, target);
    }


    // Time complexity: O(n^2)
    // Space complexity: O(n)
    private int[] sol1(int[] nums, int target) {
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

    private int[] sol2(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();

        // O(n)
        for (int i = 0; i < nums.length; ++i) {
            m.put(nums[i], i);
        }

        // O(n)
        for (int i = 0; i < nums.length; ++i) {
            if (m.get(target - nums[i]) != null && m.get(target - nums[i]) != i) {
                return new int[] {i, m.get(target - nums[i])};
            }
        }
        return null;
    }

    private int[] sol3(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; ++i) {
            int complement = target - nums[i];
            if (m.containsKey(complement) && m.get(complement) != i) {
                return new int[] {i, m.get(complement)};
            }
            m.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    private int[] sol4(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; ++i) {
            int complement = target - nums[i];
            if (m.containsKey(nums[i])) {
                return new int[] {i, m.get(nums[i])};
            }
            m.put(complement, i);
        }
        throw new IllegalArgumentException("No two sum solution");
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
