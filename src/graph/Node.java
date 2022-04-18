package graph;

import java.util.ArrayList;

public class Node {
	// 节点值/编号
	public int value;
	// 入度
	public int in;
	// 出度
	public int out;
	// 邻居
	public ArrayList<Node> nexts;
	public ArrayList<Edge> edges;

	public Node(int val) {
		value = val;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
