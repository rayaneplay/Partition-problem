import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

   public static int[] T;


    public static int[] genInstance(int size_t){
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter the size of your array");
//        int size_t = Integer.parseInt(myObj.nextLine());

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


    public static void main(String[] args) throws IOException {

        ArrayList<int[]> result1 = new ArrayList<>();
        ArrayList<int[]> result = new ArrayList<>();

        ArrayList<int[]> translatedResult = new ArrayList<>();
        Node node = new Node();
       // printArray(T);
//        int counter =0;
//        long startTime,endTime;
//        double executionTime;

        // MedianElementHeuristic h1 = new MedianElementHeuristic();
        MaximumElementHeuristic h2 = new MaximumElementHeuristic();
        SumOfRemainingElementsHeuristic h3 = new SumOfRemainingElementsHeuristic();



        // Create the CSV file
        FileWriter fw = new FileWriter("results2.csv");
        BufferedWriter bw = new BufferedWriter(fw);

        // Write the header row
        bw.write("Size,DFS,Astar (h2),Astar (h3)\n");

        // Loop over different sizes
        for(int i = 3; i < 25; i++){
            T = genInstance(i);

            // Measure execution time for DFS
            long startTime = System.currentTimeMillis();
            DFS.search();
            long endTime = System.currentTimeMillis();
            double dfsTime = (endTime - startTime) / 1000.0;

            // Measure execution time for Astar (h2)
            startTime = System.currentTimeMillis();
            Astar.search(h2);
            endTime = System.currentTimeMillis();
            double h2Time = (endTime - startTime) / 1000.0;

            // Measure execution time for Astar (h3)
            startTime = System.currentTimeMillis();
            Astar.search(h3);
            endTime = System.currentTimeMillis();
            double h3Time = (endTime - startTime) / 1000.0;

            // Write the results to the CSV file
            bw.write(i + "," + dfsTime + "," + h2Time + "," + h3Time + "\n");
        }

        // Close the file
        bw.close();
        fw.close();
    }



       // T= new int[]{6, 3, 8, 2, 12,  5,  0, 4, 23, 9,18, 1, 6, 6, 4, 9, 20,8, 9,5,51, 24, 26, 1};
        //T= new int[]{31, 10, 20, 19, 4, 3, 6};
        //T=new int[]{25, 35, 45,  5, 25,  3,  2,  2};
        //T=new int[]{3, 4, 3, 1, 3, 2, 3, 2, 1};
        //T=new int[]{2, 10, 3, 8, 5, 7, 9, 5, 3, 2};
       // T=new int[]{484, 114, 205, 288, 506, 503, 201, 127, 410};
        //T=new int[]{23, 31,  29,  44,  53,  38,  63, 85, 89, 82};
        //T=new int[]{771, 121, 281, 854, 885, 734,  486, 1003, 83, 62};
        //T=new int[]{70, 73, 77, 80, 82, 87, 90, 94, 98, 106, 110, 113, 115, 118, 120};
       // T=new int[]{382745, 799601, 909247, 729069, 467902,  44328,  34610, 698150, 823460, 903959, 853665, 551830, 610856, 670702, 488960, 951111, 323046, 446298, 931161,  31385, 496951, 264724, 224916, 169684};

//
//         startTime = System.currentTimeMillis();
//        result = Astar.search(h2);
//         endTime = System.currentTimeMillis();
//         executionTime = (endTime - startTime) / 1000.0;
//        System.out.println("Execution time h2: " + executionTime + " seconds");

//        for(int  [] i : result){
//            translatedResult.add(node.translate(i).get(0));
//            translatedResult.add(node.translate(i).get(1));
//        }
//
//
//        for(int i=0;i< translatedResult.size();i=i+2){
//            System.out.println("Solution number : " + i/2 );
//            printArray(translatedResult.get(i));
//            printArray(translatedResult.get(i+1));
//            System.out.println("The difference between the 2 subsets is : " + Math.abs(sumArray(translatedResult.get(i))-sumArray(translatedResult.get(i+1))));
//        }
//
//         startTime = System.currentTimeMillis();
//         result = Astar.search(h3);
//         endTime = System.currentTimeMillis();
//         executionTime = (endTime - startTime) / 1000.0;
//        System.out.println("Execution time h3: " + executionTime + " seconds");
//
//        startTime = System.currentTimeMillis();
//        result = DFS.search();
//        endTime = System.currentTimeMillis();
//        executionTime = (endTime - startTime) / 1000.0;
//        System.out.println("Execution time DFS: " + executionTime + " seconds");

//        for(int  [] i : result){
//            translatedResult.add(node.translate(i).get(0));
//            translatedResult.add(node.translate(i).get(1));
//        }
//
//        System.out.println(result.size());
//        for(int i=0;i< translatedResult.size();i=i+2){
//            System.out.println("Solution number : " + i/2 );
//            printArray(translatedResult.get(i));
//            printArray(translatedResult.get(i+1));
//            System.out.println("The difference between the 2 subsets is : " + Math.abs(sumArray(translatedResult.get(i))-sumArray(translatedResult.get(i+1))));
//        }


    }
