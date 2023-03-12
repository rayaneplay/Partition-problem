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


     //T = genInstance();
T= new int[]{6, 3, 8, 2, 12,  5,  0, 4, 23, 9,18, 1, 6, 6, 4, 9, 20,8, 9,5,51, 24, 26, 1};
      //T= new int[]{31, 10, 20, 19, 4, 3, 6};
//        System.out.println(T.length);
        //T=new int[]{25, 35, 45,  5, 25,  3,  2,  2};
        //T=new int[]{3, 4, 3, 1, 3, 2, 3, 2, 1};
        //T=new int[]{2, 10, 3, 8, 5, 7, 9, 5, 3, 2};
      //T=new int[]{484, 114, 205, 288, 506, 503, 201, 127, 410};
        //T=new int[]{23, 31,  29,  44,  53,  38,  63, 85, 89, 82};
        //T=new int[]{771, 121, 281, 854, 885, 734,  486, 1003, 83, 62};
        //T=new int[]{70, 73, 77, 80, 82, 87, 90, 94, 98, 106, 110, 113, 115, 118, 120};
        //T=new int[]{382745, 799601, 909247, 729069, 467902,  44328,  34610, 698150, 823460, 903959, 853665, 551830, 610856, 670702, 488960, 951111, 323046, 446298, 931161,  31385, 496951, 264724, 224916, 169684};
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> translatedResult = new ArrayList<>();
        Node node = new Node();
        printArray(T);
        int counter =0;

        long startTime = System.currentTimeMillis();

        result = DFS.search();

    printArray(result.get(1));





        long endTime = System.currentTimeMillis();
        double executionTime = (endTime - startTime) / 1000.0;
        System.out.println("Execution time: " + executionTime + " seconds");

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