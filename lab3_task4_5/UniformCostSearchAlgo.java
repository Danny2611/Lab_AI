package lab3_task4_5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import lab3_task1_2.Edge;
import lab3_task1_2.ISearchAlgo;
import lab3_task1_2.Node;

public class UniformCostSearchAlgo implements ISearchAlgo {

	PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComperator());
	Set<Node> explored = new HashSet<>();

	@Override
	public Node execute(Node root, String goal) {
		double pathCost = 0;
		root.setPathCost(pathCost);
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			System.out.print(currentNode + ":" + currentNode.getPathCost() + " ");
			if (currentNode.getLabel().equals(goal))
				return currentNode;
			explored.add(currentNode);
			List<Edge> edges = currentNode.getChildren();
			for (Edge e : edges) {
				e.getEnd().setPathCost(currentNode.getPathCost() + e.getWeight());
				if (!explored.contains(e.getEnd()) && !frontier.contains(e.getEnd())) {
					frontier.add(e.getEnd());
				}
				if (frontier.contains(e.getEnd()) && compareNode(e.getEnd())) {
//					if (compareNode(e.getEnd())) {
					frontier.remove(e.getEnd());
					frontier.add(e.getEnd());
//					}

				}
				e.getEnd().setParent(currentNode);
			}
			System.out.println(frontier.toString());
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		double pathCost = 0;
		root.setPathCost(pathCost);
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(start)) {
				currentNode.setPathCost(0.0);
				frontier.clear();
				currentNode.setParent(null);
			}
			System.out.print(currentNode + ":" + currentNode.getPathCost() + " ");
			if (currentNode.getLabel().equals(goal))
				return currentNode;
			explored.add(currentNode);
			List<Edge> edges = currentNode.getChildren();
			for (Edge e : edges) {
				e.getEnd().setPathCost(currentNode.getPathCost() + e.getWeight());
				if (!explored.contains(e.getEnd()) && !frontier.contains(e.getEnd())) {
					frontier.add(e.getEnd());
				}
				if (frontier.contains(e.getEnd()) && compareNode(e.getEnd())) {
					frontier.remove(e.getEnd());
					frontier.add(e.getEnd());

				}
				e.getEnd().setParent(currentNode);
			}
			System.out.println(frontier.toString());
		}
		return null;
	}

	public boolean compareNode(Node node) {
		Iterator<Node> iteratorNode = frontier.iterator();
		while (iteratorNode.hasNext()) {
			Node e = iteratorNode.next();
			if (e.getLabel().equals(node.getLabel()))
				return node.getPathCost() < e.getPathCost();
		}
		return false;
	}

}
