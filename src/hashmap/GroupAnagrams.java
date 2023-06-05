package hashmap;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/group-anagrams/">...</a>
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 字符按字典序排序
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars);
            if (!map.containsKey(sortedStr)) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortedStr, list);
            } else {
                map.get(sortedStr).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}
