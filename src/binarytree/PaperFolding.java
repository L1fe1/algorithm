package binarytree;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 */
public class PaperFolding {

	public static void printAllFolds(int N) {
		// 第一次对折，折痕为凹折痕
		printProcess(1, N, true);
	}

	/**
	 * 递归打印折痕
	 * @param i 节点层数
	 * @param N 对折此时，相当于总层数
	 * @param down true：凹，false：凸
	 */
	public static void printProcess(int i, int N, boolean down) {
		// 当前来到的层数大于总层数，直接返回
		if (i > N) {
			return;
		}
		// 对折的过程实际上就是模拟二叉树的中序遍历
		// 当前折痕的左边为为凹折痕
		printProcess(i + 1, N, true);
		System.out.print(down ? "凹" : "凸");
		// 当前折痕的右边为凸折痕
		printProcess(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 3;
		printAllFolds(N);
	}
}
