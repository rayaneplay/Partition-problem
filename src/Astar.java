import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Astar {

    public static ArrayList<int[]> search(Heuristic heuristic) {

        int[] T = Main.T;
        int[] subset = new int[0];
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.getCost() + heuristic.calculate(n)));
        ArrayList<int[]> arraySolutions = new ArrayList<>();
        int bestMin = Integer.MAX_VALUE;

        Node root = new Node(subset, null, 0);
        root.setCost(0);
        queue.add(root);

        while (!queue.isEmpty()) {

            int tempDiff = 0;
            Node node = queue.poll();

            if (node.getLevel() == T.length) {
                if (evaluate(node.getState()) == 0 || evaluate(node.getState())==1) {
                    arraySolutions.add(node.getState());
                    //uncomment this if we want to get the first solution only
                    //break;
                }
            } else {
                ArrayList<Node> currentChildren = node.genChildren();
                for (Node currentChild : currentChildren) {
                    currentChild.setCost(node.getCost() + evaluate(currentChild.getState()));
                    queue.add(currentChild);
                }
            }
        }

        return arraySolutions;
    }

    public static int evaluate(int[] solutions) {
        int sum0 = 0;
        int sum1 = 0;
        for (int i = 0; i < solutions.length; i++) {
            if (solutions[i] == 0) {
                sum0 = sum0 + Main.T[i];
            } else {
                sum1 = sum1 + Main.T[i];
            }
        }
        return Math.abs(sum0 - sum1);
    }
}
