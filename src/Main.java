import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

   public static int[] T;


    public static int[] genInstance(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the size of your array");
        int size_t = Integer.parseInt(myObj.nextLine());
        int [] T = new int[size_t] ;
        for(int i=0;i<size_t;i++){
            Random random = new Random();
            T[i] = random.nextInt(101);

        }
        return T;
    }

    public static void printArray(int [] T){
        for(int i : T){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static int sumArray(int[] T){
       int  sum = 0;
        for (int i : T){
            sum = sum + i;
        }
        return sum;
    }


    public static void main(String[] args) {


       T = genInstance();

        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> translatedResult = new ArrayList<>();
        Node node = new Node();
        printArray(T);
        int counter =0;
        result = DFS.search();

        for(int  [] i : result){
            translatedResult.add(node.translate(i).get(0));
            translatedResult.add(node.translate(i).get(1));
        }


        for(int i=0;i< translatedResult.size();i=i+2){
            System.out.println("Solution number : " + i/2 );
            printArray(translatedResult.get(i));
            printArray(translatedResult.get(i+1));
            System.out.println("The difference between the 2 subsets is : " + Math.abs(sumArray(translatedResult.get(i))-sumArray(translatedResult.get(i+1))));
        }

        System.out.println("The size of the search space before finding the first optimal solution is : "+ counter);











    }
}