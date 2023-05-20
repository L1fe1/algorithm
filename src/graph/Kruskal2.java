package graph;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Kruskal2 {
    public static int MAX_M = 100001;

    public static int[][] edges = new int[MAX_M][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }
            // 将边按照权重从小到大排序
            Arrays.sort(edges, 0, m, Comparator.comparingInt(a -> a[2]));
            // 构建并查集
            build(n);
            int ans = 0;
            for (int[] edge : edges) {
                // 如果两条边没有联通，则将这两条边联通并统计结果
                if (union(edge[0], edge[1])) {
                    ans += edge[2];
                }
            }
            out.println(ans);
            out.flush();
        }
    }

    public static int MAX_N = 10001;

    public static int[] parents = new int[MAX_N];

    public static int[] sizes = new int[MAX_N];

    public static int[] paths = new int[MAX_N];

    public static void build(int n) {
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    public static int find(int x) {
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

    public static boolean union(int x, int y) {
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
            return true;
        }
        return false;
    }

}
