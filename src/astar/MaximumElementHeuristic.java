import java.util.Arrays;

public class MaximumElementHeuristic implements Heuristic {
    @Override
    public int calculate(Node node) {

        int[] remaining = new int[Main.T.length-node.getLevel()];

        if (Main.T.length - node.getLevel() >= 0)
            System.arraycopy(Main.T,  node.getLevel(), remaining, 0, Main.T.length - node.getLevel());
       // System.out.println(Arrays.toString(remaining));
        int max = Integer.MIN_VALUE;
        for (int i : remaining) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

}

