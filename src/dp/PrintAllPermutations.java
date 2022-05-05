package dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrintAllPermutations {
    // 打印一个字符串的全部排列
    public static ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chars = str.toCharArray();
        process(chars, 0, res);
        return res;
    }

    // 0 ~ i-1 位置的字符已经确定了，i 位置可以选择 i ~ str.length()-1 位置的字符
    private static void process(char[] chars, int i, ArrayList<String> res) {
        if (i == chars.length) {
            res.add(String.valueOf(chars));
        }
        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            process(chars, i + 1, res);
            swap(chars, i, j);
        }
    }

    private static List<String> permutationNoRepeat(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        char[] chars = str.toCharArray();
        process2(chars, 0, res);
        return new ArrayList<>(res);
    }

    private static void process2(char[] chars, int i, ArrayList<String> res) {
        if (i == chars.length) {
            res.add(String.valueOf(chars));
        }
        // j 位置是否已经放过某个字符了
        boolean[] visited = new boolean[26];
        for (int j = i; j < chars.length; j++) {
            // j 位置已经放过 chars[j] 字符了，直接跳过
            if (!visited[chars[j] - 'a']) {
                visited[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process2(chars, i + 1, res);
                swap(chars, i, j);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "aac";
        List<String> ans1 = permutation(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutationNoRepeat(s);
        for (String str : ans2) {
            System.out.println(str);
        }
    }


}
