package binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 *
 * 员工信息的定义如下:
 * class Employee {
 *     public int happy; // 这名员工可以带来的快乐值
 *     List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 *
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。
 * 树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 */
public class MaxHappy {

	public static class Employee {
		public int happy;
		public List<Employee> subordinates;

		public Employee(int h) {
			happy = h;
			subordinates = new ArrayList<>();
		}

	}

	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}

	public static int process1(Employee cur, boolean up) {
		if (up) {
			int ans = 0;
			for (Employee next : cur.subordinates) {
				ans += process1(next, false);
			}
			return ans;
		} else {
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.subordinates) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}

	public static int maxHappy(Employee boss) {
		if (boss == null) {
			return 0;
		}
		Info all = process(boss);
		return Math.max(all.yes, all.no);
	}

	public static Info process(Employee boss) {
		if (boss.subordinates.isEmpty()) {
			return new Info(boss.happy, 0);
		}
		// 来，记录 happy 值
		int yes = boss.happy;
		// 不来
		int no = 0;
		for (Employee subordinate : boss.subordinates) {
			Info info = process(subordinate);
			// 当前员工来了，那么 happy 值为他的所有下级不来的情况下的累加值
			yes += info.no;
			// 当前员工没来，那么 happy 值为他的所有下级来或者不来的情况下的累加值的较大值
			no += Math.max(info.yes, info.no);
		}
		return new Info(yes, no);
	}

	public static class Info {
		// 来
		public int yes;
		// 不来
		public int no;

		public Info(int y, int n) {
			yes = y;
			no = n;
		}
	}

	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.subordinates.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != maxHappy(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
