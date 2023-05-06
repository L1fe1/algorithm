package binary_search;

/**
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 */
public class SplitArrayLargestSum {
    public static int splitArray(int[] nums, int m) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int l = 0, r = sum;
        int mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (needsSubArrayNum(nums, mid) <= m) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private static int needsSubArrayNum(int[] nums, int sumMax) {
        int sum = 0;
        int count = 1;
        for (int num : nums) {
            if (num > sumMax) {
                return Integer.MAX_VALUE;
            }
            sum += num;
            if (sum > sumMax) {
                count ++;
                sum = num;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int m = 2;
        System.out.println(splitArray(nums, m));
    }
}
