package graph;

public class GraphGenerator {

	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	public static Graph createGraph(Integer[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) {
			int weight = matrix[i][0];
			int fromVal = matrix[i][1];
			int toVal = matrix[i][2];
			// 添加节点
			if (!graph.nodes.containsKey(fromVal)) {
				graph.nodes.put(fromVal, new Node(fromVal));
			}
			if (!graph.nodes.containsKey(toVal)) {
				graph.nodes.put(toVal, new Node(toVal));
			}
			Node from = graph.nodes.get(fromVal);
			Node to = graph.nodes.get(toVal);
			// 设置 from、to 节点的出度、入度
			from.out ++;
			to.in ++;
			// 创建边
			Edge edge = new Edge(weight, from, to);
			// 设置 from 节点的邻居和边
			from.nexts.add(to);
			from.edges.add(edge);
			// 添加边
			graph.edges.add(edge);
		}
		return graph;
	}

}
