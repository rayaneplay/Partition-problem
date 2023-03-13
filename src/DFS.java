import java.util.ArrayList;
import java.util.Stack;

public class DFS {

    public static void printArray(int[] t) {
        for (int i : t) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static ArrayList<int[]> search() {

        int[] T = Main.T;
        int[] subset = new int[0];
        Stack<Node> stack = new Stack<>();
        ArrayList<int[]> arraySolutions = new ArrayList<>();
        int bestMin = Integer.MAX_VALUE;
        Stack<Node> solutionStack = new Stack<>();


        Node root = new Node(subset,null,0);
        stack.push(root);

        while (!stack.empty()) {

            Node node = stack.pop();

            int tempDiff = 0;

            if (node.getLevel()  == T.length) {
                tempDiff = evaluate(node.getState());


                //Just for testing purposes
                if(tempDiff < bestMin){
                    node.setDiff(tempDiff);
                    solutionStack.push(node);
                    bestMin = tempDiff;

                }

                if(tempDiff == bestMin){
                    node.setDiff(tempDiff);
                    solutionStack.push(node);
                }



            } else {
                ArrayList<Node> currentChildren = node.genChildren();
                for (Node currentChild : currentChildren) {
                    stack.push(currentChild);


                }
            }
        }

        while(solutionStack.peek().getDiff()==bestMin){

            arraySolutions.add(solutionStack.pop().getState());

        }

        return arraySolutions;
    }
    public static int evaluate(int [] solutions){
            int sum0 = 0;
            int sum1 = 0;
        for(int i=0;i<solutions.length;i++){
            if(solutions[i]==0){
                sum0 = sum0+Main.T[i];
            }else{
                sum1 = sum1 + Main.T[i];
            }
        }


        return Math.abs(sum0-sum1);

    }


}
