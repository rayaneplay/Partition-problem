import java.lang.reflect.Array;
import java.util.*;

public class Node {

    int[] state;//This the content of our node at any given time
    Node parent;

    int diff;
    int level;

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getDiff() {
        return diff;
    }

    public int getLevel() {
        return level;
    }

    public int[] getState() {
        return state;
    }

    public Node(int[] state, Node parent, int level) {
        this.state = state;
        this.parent = parent;
        this.level = level;
    }

    public Node(Node parent, int level) {
        this.parent = parent;
        this.level = level;
        this.state = new int[parent.getState().length + 1];
    }

    public Node() {

    }

    public Node(int level) {
        this.level = level;
    }

    public Node(int[] state) {
        this.state = state;

        this.level = 0;
    }

    public void printNode(Node node) {

        for (int i : node.getState()) {
            System.out.print(i + " ");

        }
        System.out.println();
    }

    public ArrayList<Node> genChildren() {

        int size = this.getState().length;
        ArrayList<Node> childrenState = new ArrayList<>();

        Node child1 = new Node(this, this.level + 1);
        Node child0 = new Node(this, this.level + 1);




        System.arraycopy(this.getState(), 0, child1.state, 0, size);
        child1.state[level] = 1;
        childrenState.add(child1);

        System.arraycopy(this.getState(), 0, child0.state, 0, size);
        child0.state[level] = 0;
        childrenState.add(child0);

        return childrenState;


    }

    //This methode takes a binary state calls translate then evaluates the diffrence between the 2 subsets
//public int evaluate(int [] solutions){
//
//        for(int i=0;i<solutions.length;i++){
//            if(solutions[i]==0){
//                sum0=sum0+Main.T[i];
//            }else{
//                sum1 = sum1 + Main.T[i];
//            }
//        }
//
//
//    return Math.abs(sum0-sum1);
//
//}

//This methode takes a binary state and translates it into integer values
    public ArrayList<int[]> translate(int[] state) {
        int[] setA = new int[Main.T.length];
        int[] setB = new int[Main.T.length];
        ArrayList<int[]> temp = new ArrayList<>();
        int k = 0, j = 0;

        for (int i=0;i<state.length;i++) {
            if (state[i] == 1) {
                setA[k] = Main.T[i];
                k++;
            } else {
                setB[j] = Main.T[i];
                j++;
            }
        }
        temp.add(setA);
        temp.add(setB);

        return temp;

    }
}


