package array;

public class TrappingRainWater {
    public static int trap1(int[] height) {
        int len = height.length;
        if (len < 3) {
            return 0;
        }
        // 0...i 位置最高的柱子高度
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // i...len-1 位置最高的柱子高度
        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            // 以 leftMax 和 rightMax 中高度较小的柱子结算
            ans += Math.max(Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i], 0);
        }
        return ans;
    }

    public static int trap2(int[] height) {
        int len = height.length;
        if (len < 3) {
            return 0;
        }
        // 左边最高的柱子
        int leftMax = height[0];
        // 右边最高的柱子
        int rightMax = height[len - 1];
        int l = 1;
        int r = len - 2;
        int ans = 0;
        // 首尾指针
        while (l <= r) {
            if (leftMax <= rightMax) {
                // 结算左边
                ans += Math.max(0, leftMax - height[l]);
                leftMax = Math.max(leftMax, height[l++]);
            } else {
                // 结算右边
                ans += Math.max(0, rightMax - height[r]);
                rightMax = Math.max(rightMax, height[r--]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {2, 0, 2};
        System.out.println(trap2(height));
    }
}
