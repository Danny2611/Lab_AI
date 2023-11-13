package lab6;

import java.util.List;

public class HillClimbingSearchNQueen {
    private static int stepClimbed = 0;
    private static int stepClimbedAfterRandomRestart = 0;
    private static int randomRestarts = 0;

    public static Node randomRestartHillClimbing(Node initialState) {
        Node state = executeHillClimbingWithRandomRestart(initialState, Integer.MAX_VALUE);

        
        while (computeH(state) != 0) {
          
            state.generateBoard();
            state = executeHillClimbingWithRandomRestart(state, Integer.MAX_VALUE);
        }

        return state;
    }

    public static int computeH(Node state) {
        return state.getH();
    }

    public static Node executeHillClimbingWithRandomRestart(Node initialState, int maxRestarts) {
        Node bestSolution = null;
        int bestSolutionH = Integer.MAX_VALUE;

        for (int restart = 0; restart < maxRestarts; restart++) {
            Node current = execute(initialState);
            int currentH = current.getH();
            stepClimbedAfterRandomRestart += stepClimbed;

            if (currentH < bestSolutionH) {
                bestSolution = current;
                bestSolutionH = currentH;
            }
        }

        return bestSolution;
    }

    public static Node execute(Node initialState) {
        Node current = new Node(initialState);
        int currentH = current.getH();

        while (true) {
            List<Node> neighbors = current.generateAllCandidates();
            Node next = null;
            int nextH = currentH;

            for (Node neighbor : neighbors) {
                int neighborH = neighbor.getH();
                if (neighborH < nextH) {
                    next = neighbor;
                    nextH = neighborH;
                }
            }

            if (next == null || nextH >= currentH) {
                // Reached a local optimum or plateau
                break;
            }

            current = next;
            currentH = nextH;
            stepClimbed++;
        }

        return current;
    }
    
}


