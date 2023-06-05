package binary_search;

/**
 * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/">...</a>
 */
public class SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int m;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                return m;
            }
            // l, m, r 位置的数字相等，无法确定旋转的断点
            if (nums[l] == nums[m] && nums[m] == nums[r]) {
                while (l != m && nums[l] == nums[m]) {
                    // l 往右走到不相等的位置
                    l ++;
                }
                if (l == m) {
                    // 如果从 l 到 m 一路上都相等，去到右半部分二分
                    l = m + 1;
                    continue;
                }
            }
            // 要么 l 走到了一个不相等的位置，要么 l, m, r 位置的数字不全相等
            if (nums[l] != nums[m]) {
                // l, m 位置的数字不相等
                if (nums[l] < nums[m]) {
                    // l 到 m 之间有序
                    if (nums[l] <= target && nums[m] > target) {
                        // target 在 l 和 m 之间
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                } else {
                    // m 到 r 之间有序
                    if (nums[r] >= target && nums[m] < target) {
                        // target 在 m 和 r 之间
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
            } else {
                // l, m 位置的数字相等，但是和 r 位置的数字不相等
                if (nums[m] < nums[r]) {
                    // m 到 r 之间有序
                    if (nums[r] >= target && nums[m] < target) {
                        // target 在 m 和 r 之间
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                } else {
                    // l 到 m 之间有序
                    if (nums[l] <= target && nums[m] > target) {
                        // target 在 l 和 m 之间
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 3));
    }
}
