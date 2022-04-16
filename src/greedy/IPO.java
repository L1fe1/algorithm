package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		// 花费最小的小根堆
		PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new MinCostComparator());
		// 利润最大的大根堆
		PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
		// 构建花费最小的小根堆
		for (int i = 0; i < Capital.length; i++) {
			minCostQueue.add(new Program(Profits[i], Capital[i]));
		}
		for (int i = 0; i < K; i++) {
			// 当初始资金 W 大于等于 minCostQ 中的花费，从 minCostQ 弹出构建利润最大的大根堆
			while (!minCostQueue.isEmpty() && maxProfitQueue.peek().c <= W) {
				maxProfitQueue.add(minCostQueue.poll());
			}
			// 如果利润最大的大根堆为空，直接返回 W
			if (maxProfitQueue.isEmpty()) {
				return W;
			}
			// 否则将利润累加到初始资金 W 中
			W += maxProfitQueue.poll().p;
		}
		return W;
	}

	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}

	}

	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}

}
