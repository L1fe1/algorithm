package greedy;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Light {

	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}

	public static int process(char[] str, int index, HashSet<Integer> lights) {
		if (index == str.length) {
			for (int i = 0; i < str.length; i++) {
				if (str[i] != 'X') {
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		} else {
			int no = process(str, index + 1, lights);
			int yes = Integer.MAX_VALUE;
			if (str[index] == '.') {
				lights.add(index);
				yes = process(str, index + 1, lights);
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}

	public static int minLight(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		char[] charArr = road.toCharArray();
		int index = 0;
		// 需要的灯数
		int lights = 0;
		while (index < charArr.length) {
			// 如果当前位置为墙，不能放灯，跳到下一个位置
			if (charArr[index] == 'X') {
				index ++;
			} else {
				// 如果当前位置为居名点，那么需要放灯
				lights ++;
				// 如果这是最后一个位置，结束返回
				if (index + 1 == charArr.length) {
					return lights;
				}
				// 如果下一个位置为墙，那么将灯放在 index 位置，然后跳到墙后的位置
				if (charArr[index + 1] == 'X') {
					index += 2;
				} else {
					// 否则，将灯放在 index + 1 的位置
					// 这样不仅能覆盖到 index 位置的居名点，而且无论 index + 2 的位置是墙或者居名点，灯都能覆盖到
					// 然后，跳到 index + 3 的位置继续
					index += 3;
				}
			}
		}
		return lights;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLight1(test);
			int ans2 = minLight(test);
			if (ans1 != ans2) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
