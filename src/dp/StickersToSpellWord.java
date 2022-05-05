package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，
 * 含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 */
public class StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        int len = stickers.length;
        // key 字符串需要 value 张贴纸
        Map<String, Integer> dp = new HashMap<>();
        // 记录每一张贴纸的字母频率表
        int[][] map = new int[len][26];
        for (int i = 0; i < len; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                map[i][c - 'a'] ++;
            }
        }
        // 空字符串需要 0 张贴纸
        dp.put("", 0);
        return process1(dp, map, target);
    }

    // target，剩余的目标字符串
    private static int process1(Map<String, Integer> dp, int[][] map, String target) {
        // 如果算过了，直接返回
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        // target 字符串所含字符的词频统计表
        int[] targetMap = new int[26];
        char[] targetArr = target.toCharArray();
        for (char c : targetArr) {
            targetMap[c - 'a'] ++;
        }
        int ans = Integer.MAX_VALUE;
        // 尝试每一张贴纸
        for (int i = 0; i < map.length; i++) {
            // 小贪心：筛选出包含 target 第一个字符的贴纸
            // 1. 因为答案与字符消除的顺序无关，所以可以选择先消除 target 的第一个字符
            // 2. 防止不包含 target 字符的贴纸尝试，导致递归死循环
            if (map[i][targetArr[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                // 使用完第 i 张贴纸之后，targetMap 中应该剩余的 j 位置字符数
                int rest = targetMap[j] - map[i][j];
                // 只有 target 需要 j 位置的字符而且使用完贴纸之后还剩余对应字符时，才加到剩余的目标字符串中
                if (targetMap[j] > 0 && rest > 0) {
                    for (int k = 0; k < rest; k ++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            String s = sb.toString();
            // 继续拼剩余的目标字符串
            int p = process1(dp, map, s);
            // 不等于 -1 说明可以使用贴纸拼成剩余的字符串，记录结果
            if (p != -1) {
                // p + 1 代表当前已经使用了一张贴纸，剩余目标字符串还需要 p 张贴纸
                ans = Math.min(ans, p + 1);
            }
        }
        // 记录缓存
        dp.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(target);
    }

    public static int minStickers2(String[] stickers, String target) {
        int n = stickers.length;
        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                map[i][c - 'a']++;
            }
        }
        char[] str = target.toCharArray();
        int[] tmap = new int[26];
        for (char c : str) {
            tmap[c - 'a']++;
        }
        HashMap<String, Integer> dp = new HashMap<>();
        int ans = process2(map, 0, tmap, dp);
        return ans;
    }

    public static int process2(int[][] map, int i, int[] tmap, HashMap<String, Integer> dp) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(i + "_");
        for (int asc = 0; asc < 26; asc++) {
            if (tmap[asc] != 0) {
                keyBuilder.append((char) (asc + 'a') + "_" + tmap[asc] + "_");
            }
        }
        String key = keyBuilder.toString();
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        boolean finish = true;
        for (int asc = 0; asc < 26; asc++) {
            if (tmap[asc] != 0) {
                finish = false;
                break;
            }
        }
        if (finish) {
            dp.put(key, 0);
            return 0;
        }
        if (i == map.length) {
            dp.put(key, -1);
            return -1;
        }
        int maxZhang = 0;
        for (int asc = 0; asc < 26; asc++) {
            if (map[i][asc] != 0 && tmap[asc] != 0) {
                maxZhang = Math.max(maxZhang, (tmap[asc] / map[i][asc]) + (tmap[asc] % map[i][asc] == 0 ? 0 : 1));
            }
        }
        int[] backup = Arrays.copyOf(tmap, tmap.length);
        int min = Integer.MAX_VALUE;
        int next = process2(map, i + 1, tmap, dp);
        tmap = Arrays.copyOf(backup, backup.length);
        if (next != -1) {
            min = next;
        }
        for (int zhang = 1; zhang <= maxZhang; zhang++) {
            for (int asc = 0; asc < 26; asc++) {
                tmap[asc] = Math.max(0, tmap[asc] - (map[i][asc] * zhang));
            }
            next = process2(map, i + 1, tmap, dp);
            tmap = Arrays.copyOf(backup, backup.length);
            if (next != -1) {
                min = Math.min(min, zhang + next);
            }
        }
        int ans = min == Integer.MAX_VALUE ? -1 : min;
        dp.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        String[] stickers = {"aaaa", "bbaa", "ccddd"};
        String target = "abcccccdddddbbaaaaa";
        System.out.println(minStickers1(stickers, target));
        System.out.println(minStickers2(stickers, target));
    }
}
