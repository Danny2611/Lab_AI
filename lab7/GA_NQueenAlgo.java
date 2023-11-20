package lab7;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {

    public static final int POP_SIZE = 100; // Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<>();
    Random rd = new Random();

    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    public Node execute() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            List<Node> newPopulation = new ArrayList<>();
            for (int j = 0; j < POP_SIZE; j++) {
                Node parent1 = getParentByTournamentSelection();
                Node parent2 = getParentByTournamentSelection();
                Node child = reproduce(parent1, parent2);
                mutate(child);
                newPopulation.add(child);
            }
            population = newPopulation;
        }
        return getBestIndividual();
    }

    // Mutate an individual by selecting a random Queen and
    // move it to a random row.
    public void mutate(Node node) {
        Queen[] queens = node.getState();
        for (int i = 0; i < queens.length; i++) {
            if (rd.nextDouble() < MUTATION_RATE) {
                queens[i].setRow(rd.nextInt(queens.length));
            }
        }
    }

    // Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
        Queen[] parent1 = x.getState();
        Queen[] parent2 = y.getState();
        Queen[] child = new Queen[parent1.length];
        int crossoverPoint = rd.nextInt(parent1.length);
        System.arraycopy(parent1, 0, child, 0, crossoverPoint);
        System.arraycopy(parent2, crossoverPoint, child, crossoverPoint, parent2.length - crossoverPoint);
        return new Node(child);
    }

    // Select K individuals from the population at random and
    // select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
        int tournamentSize = 5;
        Node best = null;
        for (int i = 0; i < tournamentSize; i++) {
            Node randomIndividual = population.get(rd.nextInt(population.size()));
            if (best == null || randomIndividual.getFitness() < best.getFitness()) {
                best = randomIndividual;
            }
        }
        return best;
    }

    // Select a random parent from the population
    public Node getParentByRandomSelection() {
        return population.get(rd.nextInt(population.size()));
    }

    // Helper method to get the best individual in the current population
    private Node getBestIndividual() {
        Node best = population.get(0);
        for (Node individual : population) {
            if (individual.getFitness() < best.getFitness()) {
                best = individual;
            }
        }
        return best;
    }
}
