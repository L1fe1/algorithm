package graph;

import disjoint_set_union.UnionFindSet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 村里面一共有 n 栋房子
 * 我们希望通过建造水井和铺设管道来为所有房子供水。
 * 对于每个房子 i，我们有两种可选的供水方案：
 * 一种是直接在房子内建造水井，
 * 成本为 wells[i - 1](注意 -1 ，因为 索引从 0 开始)
 * 另一种是从另一口井铺设管道引水，
 * 数组 pipes 给出了在房子间铺设管道的成本
 * 其中每个 pipes[j] = [house1j, house2j, costj]
 * 代表用管道将 house1j 和 house2j 连接在一起的成本
 * 连接是双向的
 * 请返回为所有房子都供水的最低总成本
 */
public class OptimizeWaterDistributionInVillage {
    public static int MAX = 100010;

    public static int[][] edges = new int[MAX][3];

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int index = 0;
        // 构建虚拟节点 0 和各个房子之间的边
        for (int i = 0; i < wells.length; i++) {
            edges[index][0] = 0;
            edges[index][1] = i + 1;
            edges[index++][2] = wells[i];
        }
        // 构建各个房子之间管道构成的边
        for (int i = 0; i < pipes.length; i++) {
            edges[index][0] = pipes[i][0];
            edges[index][1] = pipes[i][1];
            edges[index++][2] = pipes[i][2];
        }
        // 按成本排序
        Arrays.sort(edges, 0, index, Comparator.comparingInt(a -> a[2]));
        // 构建并查集
        UnionFindSet.init(n + 1);
        int ans = 0;
        for (int i = 0; i < index; i++) {
            // 如果两条边没有联通，则将这两条边联通并统计结果
            if (!UnionFindSet.isSameSet(edges[i][0], edges[i][1])) {
                UnionFindSet.union(edges[i][0], edges[i][1]);
                ans += edges[i][2];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] wells = {100, 20, 60, 80, 40};
        int[][] pipes = {{1, 2, 60}, {2, 3, 20}, {1, 5, 40}, {5, 4, 60}, {5, 3, 50}};
        System.out.println(minCostToSupplyWater(n, wells, pipes));
    }
}
