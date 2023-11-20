package lab7;

public class TestNQueenAlgorithm {
	public static void main(String[] args) {
        GA_NQueenAlgo geneticAlgorithm = new GA_NQueenAlgo();
        
        // Initialize the population
        geneticAlgorithm.initPopulation();

        // Execute the genetic algorithm
        Node solution = geneticAlgorithm.execute();

        // Display the final solution
        System.out.println("Final Solution:");
        solution.displayBoard();
        System.out.println("Fitness (Heuristic Value): " + solution.getFitness());
    }
}
