import java.util.Arrays;

/**
 * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
 * 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
 * 绳子的边缘点碰到X轴上的点，也算盖住
 */
public class CoverMaxPoints {
    public static int getMaxPointsCanCover(int num, int[] arr) {
        // 至少能盖住一个点
        int res = 1;
        int left = 0;
        for (int right = 1; right < arr.length; right ++) {
            if (arr[right] - arr[left] <= num) {
                // 如果两点之间的距离不超过绳子长度，记录盖住的点数
                res = Math.max(res, right - left + 1);
            } else {
                // 否则，滑动左边界
                left ++;
            }
        }
        return res;
    }

    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = getMaxPointsCanCover(L, arr);
            int ans2 = getMaxPointsCanCover(L, arr);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }
    }
}
