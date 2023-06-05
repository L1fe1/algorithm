package double_pointer;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">...</a>
 */
public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        int r = 0;
        for (int l = 1; l < len; l ++) {
            if (nums[l] != nums[r]) {
                nums[++r] = nums[l];
            }
        }
        return r + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
    }
}
