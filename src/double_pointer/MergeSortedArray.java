package double_pointer;

/**
 * <a href="https://leetcode.cn/problems/merge-sorted-array/">...</a>
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i;
        // 从右到左填
        for (i = nums1.length - 1; i >= 0 && n > 0 && m > 0; i--) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[i] = nums1[--m];
            } else {
                nums1[i] = nums2[--n];
            }
        }
        while (m > 0) {
            nums1[i--] = nums1[--m];
        }
        while (n > 0) {
            nums1[i--] = nums2[--n];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
    }
}
