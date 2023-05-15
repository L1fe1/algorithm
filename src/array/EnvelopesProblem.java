package array;

import java.util.Arrays;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 */
public class EnvelopesProblem {
    public static int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        // 按照宽度从小到大排序，如果一样宽，高度大的排前面
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        // ends[i] 为长度为 i + 1 的最长递增高度子序列的最小结尾
        int[] ends = new int[len];
        ends[0] = envelopes[0][1];
        int right = 0;
        int l;
        int r;
        int ans = 1;
        for (int i = 1; i < len; i++) {
            l = 0;
            r = right;
            // 在 ends 中找到 >= 第 i 封信高度的最左位置
            while (l <= r) {
                int mid = (l + r) / 2;
                if (ends[mid] >= envelopes[i][1]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ends[l] = envelopes[i][1];
            right = Math.max(right, l);
            ans = Math.max(ans, l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] envelopes ={{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(maxEnvelopes(envelopes));;
    }
}
