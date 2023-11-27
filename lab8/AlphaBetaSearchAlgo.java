package lab8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
    public void execute(Node node) {
        int value = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // Normally, here you'd return or print the action leading to this value
        System.out.println("Best achievable score: " + value);
    }

    // Maximize the value with Alpha-Beta pruning
    public int maxValue(Node node, int alpha, int beta) {
        if (node.isTerminal()) {
            return node.getValue();
        }
        int value = Integer.MIN_VALUE;
        for (Node child : node.getChildren()) {
            value = Math.max(value, minValue(child, alpha, beta));
            if (value >= beta) {
                return value;  // Beta prune
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    // Minimize the value with Alpha-Beta pruning
    public int minValue(Node node, int alpha, int beta) {
        if (node.isTerminal()) {
            return node.getValue();
        }
        int value = Integer.MAX_VALUE;
        for (Node child : node.getChildren()) {
            value = Math.min(value, maxValue(child, alpha, beta));
            if (value <= alpha) {
                return value;  // Alpha prune
            }
            beta = Math.min(beta, value);
        }
        return value;
    }
}
