
import javafx.util.Pair;

import java.util.*;


public class GeneticAlgo {

    int gencounter;


    //This methode is used to fill the initial population
    public static PriorityQueue<Individual> genPopulation(int populationSize){


        //The array that will contain our initial population
        PriorityQueue<Individual> population = new PriorityQueue<>(Comparator.comparingInt(Individual::calculateFitnessScore));

        for(int i=0;i<populationSize;i++){
            Individual tempIndividual = new Individual();
            tempIndividual.genRandomIndividual();
            population.add(tempIndividual);
        }

        return population;
    }


    //This is Elitist selection methode i.e the best "m" individuals are saved
    public static PriorityQueue<Individual> selectElite(PriorityQueue<Individual> population,int parentsNumber){

        PriorityQueue<Individual> bestIndividuals = new PriorityQueue<>(Comparator.comparingInt(Individual::calculateFitnessScore));
        for(int i=0; i<parentsNumber;i++){
            bestIndividuals.add(population.peek());
        }
        return bestIndividuals;
    }


//    public static ArrayList<Individual> monoPointCrossover(ArrayList<Individual> parents) {
//        // Create an array list to hold the child individuals
//        ArrayList<Individual> children = new ArrayList<>();
//
//        // Loop through all pairs of parents
//        for (int i = 0; i < parents.size() - 1; i++) {
//            for (int j = i + 1; j < parents.size(); j++) {
//                // Create new child individuals
//                Individual child1 = new Individual();
//                Individual child2 = new Individual();
//
//                // Copy the parent chromosomes into the children
//                child1.setChromosome(Arrays.copyOf(parents.get(i).getChromosome(), parents.get(i).getChromosome().length));
//                child2.setChromosome(Arrays.copyOf(parents.get(j).getChromosome(), parents.get(j).getChromosome().length));
//
//                // Perform the crossover operation
//                Random random = new Random();
//                int crossoverPoint = random.nextInt(Main.T.length);
//                //int crossoverPoint = 3;
//
//                for (int k = crossoverPoint; k < Main.T.length; k++) {
//                    int temp = child1.getChromosome()[k];
//                    child1.getChromosome()[k] = child2.getChromosome()[k];
//                    child2.getChromosome()[k] = temp;
//                }
//
//                // Add the children to the array list
//                children.add(child1);
//                //children.add(child2);
//            }
//        }
//        children.sort(Comparator.comparingInt(Individual::calculateFitnessScore));
//        return children;
//    }


    //we have to pass the arrayList of selected parents to this methode, it should only take the selected parents and
    //Not the whole population
    //THE SAMPLE PARENTS SIZE IS SELECTED IN THE SELECTION METHODE AND NOT THE CROSSOVER
    //IT ONLY RETURNS THE CHILDREN
    public static PriorityQueue<Individual> monoPointCrossover(PriorityQueue<Individual> parents){

        Individual child1 = new Individual();
        Individual child2 = new Individual();


        //An array list containing the 2 parents and their 2 kids
        PriorityQueue<Individual> children = new PriorityQueue<>(Comparator.comparingInt(Individual::calculateFitnessScore));

                // Copy the parent chromosomes into the children
                child1.setChromosome(Arrays.copyOf(parents.peek().getChromosome(), Main.T.length));
                child2.setChromosome(Arrays.copyOf(parents.peek().getChromosome(), Main.T.length));

                // Perform the crossover operation
                Random random = new Random();
                int crossoverPoint = random.nextInt(Main.T.length);


                for (int k = crossoverPoint; k < Main.T.length; k++) {
                    int temp = child1.getChromosome()[k];
                    child1.getChromosome()[k] = child2.getChromosome()[k];
                    child2.getChromosome()[k] = temp;
                }

                children.add(child1);
                children.add(child2);

                return children;
    }


    //The mutation will happen using a taux variable (a prob for each gene)
    public static PriorityQueue<Individual> mutation(PriorityQueue<Individual> children, double alpha) {
        Random random = new Random();

        for (Individual child : children) {
            for (int i = 0; i < child.getChromosome().length; i++) {
                boolean mutate = random.nextInt(100) < (alpha);
                if (mutate) {
                    child.getChromosome()[i] = (child.getChromosome()[i] == 0) ? 1 : 0;
                }
            }
        }

        return children;
    }



    public static boolean solutionFound(PriorityQueue<Individual> population){
        for(Individual individual : population){

            if(individual.calculateFitnessScore() == 1 || individual.calculateFitnessScore() == 0 ){
                return true;
            }
        }
        return false;
    }

    public static Pair<Individual,Integer> search(double alpha, int populationSize, int generationLimit){
        Individual bestSolution;
        PriorityQueue<Individual> population;
        population = genPopulation(populationSize);
        int generationCounter = 0;

        while(population.peek().getFitnessScore() > 1 &&  generationCounter < generationLimit){

            PriorityQueue<Individual> elite;


            elite = selectElite(population,2);

            PriorityQueue<Individual> children;

            children = monoPointCrossover(elite);
            mutation(children,alpha);

            population.addAll(children);

            PriorityQueue<Individual> temp = new PriorityQueue<>(Comparator.comparingInt(Individual::calculateFitnessScore));;


            for(int i=0; i<populationSize-2; i++){
                temp.add(population.poll());

            }

            population.clear();
            population.addAll(temp);


            generationCounter++;


        }


        bestSolution = population.peek();
        return new Pair<>(bestSolution,generationCounter);
    }

}
