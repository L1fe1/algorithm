package graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	// 节点编号 -> 节点
	public HashMap<Integer, Node> nodes;
	// 边
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
