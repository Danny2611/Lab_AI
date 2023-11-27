package lab8;

public class Test {

    public static void main(String[] args) {
       
        Node root = new Node("Root");
        Node child1 = new Node("Child1", 10);
        Node child2 = new Node("Child2", 20);
        root.addChild(child1);
        root.addChild(child2);

      
        System.out.println("Using MiniMaxSearchAlgo:");
        MiniMaxSearchAlgo miniMaxSearchAlgo = new MiniMaxSearchAlgo();
        miniMaxSearchAlgo.execute(root);

        System.out.println("------------------------------");

        
        System.out.println("Using AlphaBetaSearchAlgo:");
        AlphaBetaSearchAlgo alphaBetaSearchAlgo = new AlphaBetaSearchAlgo();
        alphaBetaSearchAlgo.execute(root);
    }
}
