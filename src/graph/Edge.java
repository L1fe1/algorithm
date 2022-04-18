package graph;

public class Edge {
    // 权重
    public int weight;
    // 来源节点
    public Node from;
    // 去向节点
    public Node to;

    public Edge(int w, Node f, Node t) {
        weight = w;
        from = f;
        to = t;
    }
}
