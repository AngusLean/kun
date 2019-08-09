package com.doubleysoft.kun.ioc;

/**
 * @author cupofish@gmail.com
 * 8/4/19 09:35
 */
public class Test {
    public static int removeDuplicates(int[] nums) {
        int len         = nums.length;
        int targetIndex = 0;
        int dupIndex    = 0;
        int totalDup    = 0;
        while (targetIndex < len - 1) {
            int compareSource = nums[dupIndex];
            while (dupIndex + 1 < len && nums[++dupIndex] == compareSource) {
                totalDup++;

            }
            nums[++targetIndex] = nums[dupIndex];

        }
        return totalDup;

    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3};
        int   dup = removeDuplicates(arr);
        System.out.println(dup);
        System.out.println(arr);
    }
}
