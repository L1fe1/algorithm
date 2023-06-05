package binary_search;

/**
 * <a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/">...</a>
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = lessMostRight(nums, target) + 1;
        if (l == nums.length || nums[l] != target) {
            // 没找到小于 target 最右的位置或者找到的位置的下一个元素不等于 target
            return new int[]{-1, -1};
        }
        return new int[]{l, lessMostRight(nums, target + 1)};
    }

    // 查找 nums 中小于 target 最右边的位置
    public static int lessMostRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 11;
        int[] ans = searchRange(nums, target);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
