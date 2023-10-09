package lab3_task4_5;

import java.util.Comparator;

import lab3_task1_2.Node;

public class NodeComperator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		return (int) (o1.getPathCost() - o2.getPathCost());
	}

}
