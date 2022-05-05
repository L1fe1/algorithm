package dp;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明（博弈论的基础：双方玩家都不会使得对方在单独改变策略的情况下获得更大收益）。
 * 请返回最后获胜者的分数。
 * 范围上尝试的模型
 */
public class CardsInLine {
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(offensive(arr, 0, arr.length - 1), defensive(arr, 0, arr.length - 1));
    }

    // 后手
    private static int defensive(int[] arr, int l, int r) {
        // 最后一张牌了，由于是后手，所以没得选，返回 0
        if (l == r) {
            return 0;
        }
        // 由于是后手，该轮没得选
        // 先手方可以选择左边或者右边的牌，那么下一轮后手方以先手的视角从剩下的牌中做选择
        // 先手方必定给后手方留下较小的牌让他选
        int left = offensive(arr, l + 1, r);
        int right = offensive(arr, l , r - 1);
        return Math.min(left, right);
    }

    // 先手
    private static int offensive(int[] arr, int l, int r) {
        // 最后一张牌了，由于是先手，所以拿掉最后一张牌
        if (l == r) {
            return arr[l];
        }
        // 先手方可以选择左边或者右边的牌，，那么下一轮先手方以后手的视角从剩下的牌中做选择
        // 先手方必定会做能拿到更大分数的选择
        int left = arr[l] + defensive(arr, l + 1, r);
        int right = arr[r] + defensive(arr, l , r - 1);
        return Math.max(left, right);
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int l = arr.length;
        // 先手
        int[][] offensive = new int[l][l];
        // 后手
        int[][] defensive = new int[l][l];
        // R：0~l
        for (int i = 0; i < l; i++) {
            // L = R
            offensive[i][i] = arr[i];
            // L：R-1~0
            for (int j = i - 1; j >= 0; j--) {
                // offensive 任意 (l,r) 位置都由其对应的 defensive (l+1,r) 和 (l,r-1) 位置得出
                offensive[j][i] = Math.max(arr[i] + defensive[j + 1][i],
                        arr[j] + defensive[j][i - 1]);
                // defensive 任意 (l,r) 位置都由其对应的 offensive (l+1,r) 和 (l,r-1) 位置得出
                defensive[j][i] = Math.min(offensive[j + 1][i], offensive[j][i - 1]);
            }
        }
        return Math.max(offensive[0][l - 1], defensive[0][l - 1]);
    }

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        // 先手
        int[][] offensive = new int[len][len];
        // 后手
        int[][] defensive = new int[len][len];
        for (int i = 0; i < len; i++) {
            // l = r
            offensive[i][i] = arr[i];
        }
        for (int i = 1; i < len; i++) {
            int l = 0;
            int r = i;
            while (l < len && r < len) {
                // offensive 任意 (l,r) 位置都由其对应的 defensive (l+1,r) 和 (l,r-1) 位置得出
                offensive[l][r] = Math.max(arr[l] + defensive[l + 1][r],
                        arr[r] + defensive[l][r - 1]);
                // defensive 任意 (l,r) 位置都由其对应的 offensive (l+1,r) 和 (l,r-1) 位置得出
                defensive[l][r] = Math.min(offensive[l + 1][r], offensive[l][r - 1]);
                l ++;
                r ++;
            }
        }
        return Math.max(offensive[0][len - 1], defensive[0][len - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }
}
