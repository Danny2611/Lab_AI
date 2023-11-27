package lab8;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
    public void execute(Node node) {
        int value = maxValue(node);
        // Normally, here you'd return or print the action leading to this value
        System.out.println("Best achievable score: " + value);
    }

    // Maximize the value
    public int maxValue(Node node) {
        if (node.isTerminal()) {
            return node.getValue();
        }
        int value = Integer.MIN_VALUE;
        for (Node child : node.getChildren()) {
            value = Math.max(value, minValue(child));
        }
        return value;
    }

    // Minimize the value
    public int minValue(Node node) {
        if (node.isTerminal()) {
            return node.getValue();
        }
        int value = Integer.MAX_VALUE;
        for (Node child : node.getChildren()) {
            value = Math.min(value, maxValue(child));
        }
        return value;
    }
}
