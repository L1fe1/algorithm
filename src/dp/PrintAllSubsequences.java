package dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintAllSubsequences {
    // 打印一个字符串的全部子序列
    public static List<String> subs(String s) {
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<>();
        String path = "";
        process(chars, 0, ans, path);
        return ans;
    }

    // 0 ~ i-1 位置已经做过选择了，从 i 位置开始做选择
    public static void process(char[] chars, int i, List<String> ans, String path) {
        // base case
        // 0 ~ chars.length - 1 也就是整个字符串都做完选择了，结算答案
        if (i == chars.length) {
            ans.add(path);
            return;
        }
        // 不选 i 位置的字符，去 i + 1 位置上做选择，path 不变
        process(chars, i + 1, ans, path);
        // 选 i 位置的字符，去 i + 1 位置上做选择，path 连接 i 位置的字符
        process(chars, i + 1, ans, path + chars[i]);
    }

    // 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    public static List<String> subsNoRepeat(String s) {
        char[] chars = s.toCharArray();
        Set<String> set = new HashSet<>();
        String path = "";
        process2(chars, 0, set, path);
        return new ArrayList<>(set);
    }

    public static void process2(char[] chars, int i, Set<String> set, String path) {
        if (i == chars.length) {
            set.add(path);
            return;
        }
        process2(chars, i + 1, set, path);
        process2(chars, i + 1, set, path + chars[i]);
    }

    public static void main(String[] args) {
        String test = "aacc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");
    }


}
