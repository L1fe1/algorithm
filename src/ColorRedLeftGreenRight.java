/**
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
 * 目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。
 * 如样例所示: s = RGRGR 我们涂染之后变成 RRRGG 满足要求了,涂染的个数为 2,没有比这个更好的涂染方案。
 */
public class ColorRedLeftGreenRight {
    public static int colorRedLeftGreenRight(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int[] right = new int[s.length()];
        right[s.length() - 1] = s.charAt(s.length() - 1) == 'R' ? 1 : 0;
        for (int i = s.length() - 2; i >= 0; i --) {
            right[i] = right[i + 1] + (s.charAt(i) == 'R' ? 1 : 0);
        }
        int res = right[0];
        int left = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            left = left + (s.charAt(i) == 'G' ? 1 : 0);
            res = Math.min(res, left + right[i + 1]);
        }
        return Math.min(res, left + (s.charAt(s.length() - 1) == 'G' ? 1 : 0));
    }

    public static int betterColorRedLeftGreenRight(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        // 记录某个位置右边 R 的数量，也就是将该位置右边（不包括它自己）全涂成 G 所需涂染的数量
        int right = 0;
        for (int i = 0; i < s.length(); i ++) {
            right += s.charAt(i) == 'R' ? 1 : 0;
        }
        // 将结果初始化为将所有位置全涂成 G 所需涂染的数量
        int res = right;
        // 记录某个位置左边 G 的数量，也就是将该位置左边（包括它自己）全涂成 R 所需涂染的数量
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            left += s.charAt(i) == 'G' ? 1: 0;
            right -= s.charAt(i) == 'R' ? 1: 0;
            // 记录每个位置将左边（包括它自己）全涂成 R 右边全涂成 G 所需涂染数量的最小值即为最终的结果
            res = Math.min(res, left + right);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(betterColorRedLeftGreenRight("RRRRRRGGRR"));
    }
}
