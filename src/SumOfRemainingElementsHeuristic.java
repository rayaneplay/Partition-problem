import java.util.Arrays;

public class SumOfRemainingElementsHeuristic implements Heuristic {
    @Override
    public int calculate(Node node) {
        int[] remaining = new int[Main.T.length-node.getLevel()];

        if (Main.T.length - node.getLevel() >= 0)
            System.arraycopy(Main.T,  node.getLevel(), remaining, 0, Main.T.length - node.getLevel());
        int sum = 0;
        for (int i : remaining) {
            sum += i;
        }
        return sum;
    }

}
