package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
    public static int MAX = 200001;

    public static int[] queue = new int[MAX];

    public static int[] inDegrees = new int[MAX];

    public static int[] ans = new int[MAX];

    public static int n, m, from, to;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            // 构建邻接表
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                in.nextToken();
                from = (int) in.nval;
                in.nextToken();
                to = (int) in.nval;
                graph.get(from).add(to);
            }
            if (!topSort(graph)) {
                out.println(-1);
            } else {
                for (int i = 0; i < n - 1; i++) {
                    out.print(ans[i] + " ");
                }
                out.println(ans[n - 1]);
            }
            out.flush();
        }
    }

    public static boolean topSort(List<List<Integer>> graph) {
        // 构建所有节点的入度表
        for (List<Integer> nexts : graph) {
            for (int next : nexts) {
                inDegrees[next] ++;
            }
        }
        int l = 0, r = 0;
        // 将入度为 0 的节点加入到队列中
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0) {
                queue[r++] = i;
            }
        }
        int index = 0;
        while (l < r) {
            // 弹出入度为 0 的节点加入到 ans 中
            int cur = queue[l++];
            ans[index++] = cur;
            // 将当前节点的所有邻居入度减 1，如果减完之后入度为 0，加入队列
            for (int next : graph.get(cur)) {
                if (--inDegrees[next] == 0) {
                    queue[r++] = next;
                }
            }
        }
        return index == n;
    }
}
