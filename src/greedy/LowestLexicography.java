package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 */
public class LowestLexicography {

	public static String lowestString1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		ArrayList<String> all = new ArrayList<>();
		HashSet<Integer> use = new HashSet<>();
		process(strs, use, "", all);
		String lowest = all.get(0);
		for (int i = 1; i < all.size(); i++) {
			if (all.get(i).compareTo(lowest) < 0) {
				lowest = all.get(i);
			}
		}
		return lowest;
	}

	public static void process(String[] strs, HashSet<Integer> use, String path, ArrayList<String> all) {
		if (use.size() == strs.length) {
			all.add(path);
		} else {
			for (int i = 0; i < strs.length; i++) {
				if (!use.contains(i)) {
					use.add(i);
					process(strs, use, path + strs[i], all);
					use.remove(i);
				}
			}
		}
	}

	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			// 如果字符串 o1 连接 o2 比 o2 连接 o1 小，那么 o1 排前面；否则 o2 排前面
			return (o1 + o2).compareTo(o2 + o1);
		}
	}

	public static String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new MyComparator());
		StringBuilder res = new StringBuilder();
		for (String str : strs) {
			res.append(str);
		}
		return res.toString();
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 5);
			ans[i] = (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	// for test
	public static String[] copyStringArray(String[] arr) {
		String[] ans = new String[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = String.valueOf(arr[i]);
		}
		return ans;
	}

	public static void main(String[] args) {
		int arrLen = 6;
		int strLen = 5;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			String[] arr1 = generateRandomStringArray(arrLen, strLen);
			String[] arr2 = copyStringArray(arr1);
			if (!lowestString1(arr1).equals(lowestString(arr2))) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
