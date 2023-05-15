package disjoint_set_union;

/**
 * n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。
 * 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。
 * 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。
 * 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。 每次交换可选择任意两人，让他们站起来交换座位。
 */
public class CouplesHoldingHands {
    public int minSwapsCouples(int[] row) {
        int len = row.length;
        UnionFind unionFind = new UnionFind(len / 2);
        for (int i = 0; i < len; i += 2) {
            // 相邻座位的人所在的组合并，组的编号为 id / 2
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        // 情侣组的数量减去并查集的数量就是要交换的次数
        return len / 2 - unionFind.sets;
    }

    //[0,2,1,3]
    public static class UnionFind {
        public int[] parents;
        public int[] paths;
        public int[] sizes;
        public int sets;

        public UnionFind(int n) {
            parents = new int[n];
            paths = new int[n];
            sizes = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
            sets = n;
        }

        public int find(int x) {
            int index = 0;
            while (x != parents[x]) {
                paths[index++] = x;
                x = parents[x];
            }
            int pathIndex = index - 1;
            while (pathIndex >= 0) {
                parents[paths[pathIndex--]] = x;
            }
            return x;
        }

        public void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (xParent != yParent) {
                if (sizes[xParent] < sizes[yParent]) {
                    parents[xParent] = yParent;
                    sizes[yParent] += sizes[xParent];
                } else {
                    parents[yParent] = xParent;
                    sizes[xParent] += sizes[yParent];
                }
                sets --;
            }
        }
    }
}
