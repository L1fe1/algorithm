package recursion;

public class CountAndSay {
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        char[] chars = countAndSay(n - 1).toCharArray();
        StringBuilder ans = new StringBuilder();
        int times = 1;
        int len = chars.length;
        for (int i = 1; i < len; i++) {
            if (chars[i] == chars[i - 1]) {
                // 数字出现的次数加 1
                times ++;
            } else {
                // 统计结果，重置计数
                ans.append(times);
                ans.append(chars[i - 1]);
                times = 1;
            }
        }
        // 处理最后的数字
        ans.append(times);
        ans.append(chars[len - 1]);
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }
}
