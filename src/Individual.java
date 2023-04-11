import java.util.ArrayList;
import java.util.Random;

public class Individual {

    private int[] chromosome;
    private int fitnessScore;

    public Individual(){

    }
    public int getFitnessScore() {
        return fitnessScore;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public void setFitnessScore(int fitness) {
        this.fitnessScore = fitness;
    }




    //This methode is used to generate our initial individuals
    public void genRandomIndividual(){

        //The array that will be filled with random values
        this.chromosome= new int[Main.T.length];

        for(int i =0;i< Main.T.length;i++){
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();

            if(randomBoolean){
                chromosome[i] = 1;
            }else{
                chromosome[i] = 0;
            }
        }

    }

    public int calculateFitnessScore(){
        int sum0 = 0;
        int sum1 = 0;
        for(int i=0;i<this.chromosome.length;i++){
            if(this.chromosome[i]==0){
                sum0 = sum0+Main.T[i];
            }else{
                sum1 = sum1 + Main.T[i];
            }
        }
        this.fitnessScore =  Math.abs(sum0-sum1);
        return Math.abs(sum0-sum1);
    }

    public static ArrayList<int[]> translate(int[] state) {
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
