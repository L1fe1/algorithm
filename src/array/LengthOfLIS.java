package array;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class LengthOfLIS {
    public int lengthOfLIS1(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < len; i++) {
            // 0...i-1 位置的最长递增子序列长度
            int preLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    preLen = Math.max(preLen, dp[j]);
                }
            }
            // i 位置的最长递增子序列长度
            dp[i] = preLen + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int[] ends = new int[len];
        // ends[i] 为长度为 i + 1 的最长递增子序列的最小结尾
        ends[0] = nums[0];
        int ans = 1;
        int right = 0;
        for (int i = 1; i < len; i++) {
            int l = 0;
            int r = right;
            // 在 ends 中找到 >= nums[i] 的最左位置
            while (l <= r) {
                int mid = (l + r) / 2;
                if (ends[mid] >= nums[i]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // 如果能找到，更新 ends 数组，否则，扩充 ends 数组
            ends[l] = nums[i];
            right = Math.max(right, l);
            ans = Math.max(ans, l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS2(arr));
    }
}
