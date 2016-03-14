package opt.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import opt.ga.NQueensFitnessFunction;
import dist.DiscreteDependencyTree;
import dist.DiscretePermutationDistribution;
import dist.DiscreteUniformDistribution;
import dist.Distribution;
import opt.DiscreteChangeOneNeighbor;
import opt.EvaluationFunction;
import opt.GenericHillClimbingProblem;
import opt.HillClimbingProblem;
import opt.NeighborFunction;
import opt.RandomizedHillClimbing;
import opt.SimulatedAnnealing;
import opt.SwapNeighbor;
import opt.example.*;
import opt.ga.CrossoverFunction;
import opt.ga.DiscreteChangeOneMutation;
import opt.ga.SingleCrossOver;
import opt.ga.GenericGeneticAlgorithmProblem;
import opt.ga.GeneticAlgorithmProblem;
import opt.ga.MutationFunction;
import opt.ga.StandardGeneticAlgorithm;
import opt.ga.SwapMutation;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;

/**
 * @author kmanda1
 * @version 1.0
 */
public class NQueensSACoolingRate {
    /** The n value */
    private static final int N = 10;
    /** The t value 
     * @throws IOException */
    
    public static void main(String[] args) throws IOException {
        int[] ranges = new int[N];
        Random random = new Random(N);
        
        
        int numIterations = 100;
        
        String outputDir = "/Users/craiglab/Documents/ml/Assignment2/nqueens/";
        String winner = "";
        double mimicBest = Integer.MIN_VALUE;
        double GABest = Integer.MIN_VALUE;
        double SABest = Integer.MIN_VALUE;
        double RHCBest = Integer.MIN_VALUE;
        double mimicWinCounter = 0;
        double GAWinCounter = 0;
        double SAWinCounter = 0;
        double RHCWinCounter = 0;
        double[] mimicTrialBests = new double[numIterations];
        double[] GATrialBests = new double[numIterations];
        double[] SATrialBests = new double[numIterations];
        double[] RHCTrialBests = new double[numIterations];

        double[] mimicTrainingTimes = new double[numIterations];
        double[] GATrainingTimes = new double[numIterations];
        double[] SATrainingTimes = new double[numIterations];
        double[] RHCTrainingTimes = new double[numIterations];
        

        double[] coolingrate = new double[numIterations];
        //tokeep[0] = 10;
        for (int i = 1; i < numIterations; i ++){
        	coolingrate[i] = i / 100.0;
        	
        }
        
        for (int i = 0; i < N; i++) {
        	ranges[i] = random.nextInt();
        }
    	
        NQueensFitnessFunction ef = new NQueensFitnessFunction();
        Distribution odd = new DiscretePermutationDistribution(N);
        NeighborFunction nf = new SwapNeighbor();
        MutationFunction mf = new SwapMutation();
        CrossoverFunction cf = new SingleCrossOver();
        Distribution df = new DiscreteDependencyTree(.1); 
        HillClimbingProblem hcp = new GenericHillClimbingProblem(ef, odd, nf);
        GeneticAlgorithmProblem gap = new GenericGeneticAlgorithmProblem(ef, odd, mf, cf);
        ProbabilisticOptimizationProblem pop = new GenericProbabilisticOptimizationProblem(ef, odd, df);
        

        
        for (int i = 0; i < numIterations; i ++){
	        
        	System.out.println("~~~~~~~~~~~~~~~RUN " + i + "~~~~~~~~~~~~~~~");
        	double cr = coolingrate[i];
        	
        	
            //simulated annealing
        	SimulatedAnnealing sa = new SimulatedAnnealing(100, cr, hcp);
	        FixedIterationTrainer fit = new FixedIterationTrainer(sa, 100);
	        double start = System.nanoTime(), end;
            fit.train();
            end = System.nanoTime();
            SATrainingTimes[i] = (end - start)/Math.pow(10,9);
            SATrialBests[i] = ef.value(sa.getOptimal());
            if(ef.value(sa.getOptimal()) > SABest){
                SABest = ef.value(sa.getOptimal());
            }
            System.out.println("SA BEST: " + ef.value(sa.getOptimal()));
            System.out.println("SA TRAINING TIME: " + SATrainingTimes[i]);
       
//            // Genetic algorithm
//	        
//	        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(200, 0, 10, gap);
//	        fit = new FixedIterationTrainer(ga, 100);
//	        start = System.nanoTime();
//	        fit.train();
//	        end = System.nanoTime();
//	        GATrainingTimes[i] = (end - start)/Math.pow(10,9);
//            GATrialBests[i] = ef.value(ga.getOptimal());
//            if(ef.value(ga.getOptimal()) > GABest){
//                GABest = ef.value(ga.getOptimal());
//            }
//            System.out.println("GA BEST: " + ef.value(ga.getOptimal()));
//            System.out.println("GA TRAINING TIME: " + GATrainingTimes[i]);
//
//            // MIMIC
//            
//	        MIMIC mimic = new MIMIC(200, 10, pop);
//	        fit = new FixedIterationTrainer(mimic, 5);
//	        start = System.nanoTime();
//            fit.train();
//            end = System.nanoTime();
//	        System.out.println(ef.value(mimic.getOptimal()));
//	        mimicTrainingTimes[i] = (end - start)/Math.pow(10,9);
//            mimicTrialBests[i] = ef.value(mimic.getOptimal());
//            if(ef.value(mimic.getOptimal()) > mimicBest){
//                mimicBest = ef.value(mimic.getOptimal());
//            }
//            System.out.println("MIMIC BEST: " + ef.value(mimic.getOptimal()));
//            System.out.println("MIMIC TRAINING TIME: " + mimicTrainingTimes[i]);
//            
//            
//            // count which algorithm is best for each iteration 
//            if(SATrialBests[i] >= mimicTrialBests[i] && SATrialBests[i] >= RHCTrialBests[i] && SATrialBests[i] >= GATrialBests[i]){
//                SAWinCounter++;
//            }
//              else if(mimicTrialBests[i] >= SATrialBests[i] && mimicTrialBests[i] >= RHCTrialBests[i] && mimicTrialBests[i] >= GATrialBests[i]){
//                mimicWinCounter++;
//            }
//            else  if(RHCTrialBests[i] >= mimicTrialBests[i] && RHCTrialBests[i] >= SATrialBests[i] && RHCTrialBests[i] >= GATrialBests[i]){
//                      RHCWinCounter++;
//            }
//            else  if(GATrialBests[i] >= mimicTrialBests[i] && GATrialBests[i] >= SATrialBests[i] && GATrialBests[i] >= RHCTrialBests[i]){
//                GAWinCounter++;
//            }
//            System.out.println();       
        }
        System.out.println("~~~~~~~~~~~~~~~~~~FINAL RESULTS~~~~~~~~~~~~~~~~~~");
//      System.out.println("Mimic Wins: " + mimicWinCounter);
//      System.out.println("SA Wins: " + SAWinCounter);
//      System.out.println("RHC Wins: " + RHCWinCounter);
//      System.out.println("GA Wins: " + GAWinCounter );
//      System.out.println();
//      if(mimicWinCounter > SAWinCounter && mimicWinCounter > GAWinCounter && mimicWinCounter > RHCWinCounter){
//          winner = "MIMIC";
//          System.out.println("MIMIC won with: " + mimicWinCounter + " wins");
//          System.out.println("MIMIC won " + 100*(mimicWinCounter/numIterations) + "% of the time");
//      }
//     else if(SAWinCounter > mimicWinCounter && SAWinCounter > GAWinCounter && SAWinCounter > RHCWinCounter){
//          winner = "SA";
//          System.out.println("SA won with: " + SAWinCounter + " wins");
//          System.out.println("SA won " + 100*(SAWinCounter/numIterations) + "% of the time");
//      }
//     else if(GAWinCounter > mimicWinCounter && GAWinCounter > SAWinCounter && GAWinCounter > RHCWinCounter){
//          winner = "GA";
//          System.out.println("GA won with: " + GAWinCounter + " wins");
//          System.out.println("GA won " + 100*(GAWinCounter/numIterations) + "% of the time");
//      }
//     else if(RHCWinCounter > mimicWinCounter && RHCWinCounter > GAWinCounter && RHCWinCounter > SAWinCounter){
//          winner = "RHC";
//          System.out.println("RHC won with: " + RHCWinCounter + " wins");
//          System.out.println("RHC won " + 100*(RHCWinCounter/numIterations) + "% of the time");
//      }
      System.out.println();
      double avgRHCScore = 0;
      double avgSAScore = 0;
      double avgGAScore = 0;
      double avgMimicScore = 0;

      for(int i = 0; i < numIterations; i++){
//          avgRHCScore += RHCTrialBests [i];
          avgSAScore += SATrialBests[i];
//          avgGAScore += GATrialBests[i];
//          avgMimicScore += mimicTrialBests[i];
      }
//      avgRHCScore = avgRHCScore / numIterations;
      avgSAScore = avgSAScore / numIterations;
//      avgGAScore = avgGAScore / numIterations;
//      avgMimicScore = avgMimicScore / numIterations;
//      System.out.println("RHC BEST RUN: " + RHCBest);
//      System.out.println("RHC AVG SCORE: " + avgRHCScore);
      System.out.println();
      System.out.println("SA BEST RUN: " + SABest);
      System.out.println("SA AVG SCORE: " + avgSAScore);
//      System.out.println();
//      System.out.println("GA BEST RUN: " + GABest);
//      System.out.println("GA AVG SCORE: " + avgGAScore);
//      System.out.println();
//      System.out.println("MIMIC BEST RUN: " + mimicBest);
//      System.out.println("MIMIC AVG SCORE: " + avgMimicScore);

//      double avgRHCTrainingTimes = 0;
      double avgSATrainingTimes = 0;
//      double avgGATrainingTimes = 0;
//      double avgMimicTrainingTimes = 0;

      for(int i = 0; i < numIterations; i++){
//          avgGATrainingTimes += GATrainingTimes[i];
//          avgRHCTrainingTimes += SATrainingTimes[i];
          avgSATrainingTimes += RHCTrainingTimes[i];
//          avgMimicTrainingTimes += mimicTrainingTimes[i];
      }
//      avgGATrainingTimes = avgGATrainingTimes / numIterations;
//      avgRHCTrainingTimes = avgRHCTrainingTimes / numIterations;
      avgSATrainingTimes = avgSATrainingTimes / numIterations;
//      avgMimicTrainingTimes = avgMimicTrainingTimes / numIterations;
      System.out.println();
//      System.out.println("RHC AVERAGE TRAINING TIME: " + avgRHCTrainingTimes );
      System.out.println("SA AVERAGE TRAINING TIME: " + avgSATrainingTimes );
//      System.out.println("GA AVERAGE TRAINING TIME: " + avgGATrainingTimes );
//      System.out.println("Mimic AVERAGE TRAINING TIME: " + avgMimicTrainingTimes );

      //output
     File file = new File(outputDir + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "_Results.csv");
      file.getParentFile().mkdirs();
      FileWriter writer = new FileWriter(file);
      PrintWriter pwtr = new PrintWriter(new BufferedWriter(writer));
//      pwtr.println("RHC SCORE, RHC TRAINING TIME, SA SCORE, SA TRAINING TIME, GA SCORE, GA TRAINING TIME, MIMIC SCORE, MIMIC TRAINING TIME");
      pwtr.println("COOLING RATE, SA SCORE, SA TRAINING TIME");
      for(int i = 0; i < numIterations; i++) {
//          pwtr.println(RHCTrialBests[i] + "," + RHCTrainingTimes[i] + "," + SATrialBests[i] + "," + SATrainingTimes[i] + "," + GATrialBests[i] + "," + GATrainingTimes[i] + "," + mimicTrialBests[i] + "," + mimicTrainingTimes[i]);
      	  pwtr.println(coolingrate[i] + "," + SATrialBests[i] + "," + SATrainingTimes[i]);

      }
//      pwtr.println();
//      pwtr.println("FINAL RESULTS");
//      pwtr.println("RHC BEST SCORE, RHC AVG SCORE, RHC AVG TIME, RHC WIN PERCENTAGE, SA BEST SCORE, SA AVG SCORE, SA AVG TIME,  SA WIN PERCENTAGE, GA BEST SCORE, GA AVG SCORE, GA AVG TIME, GA WIN PERCENTAGE, MIMIC BEST SCORE, MIMIC AVG SCORE, MIMIC AVG TIME, MIMIC WIN PERCENTAGE");
//      pwtr.println(RHCBest + "," + avgRHCScore + "," + avgRHCTrainingTimes + "," + 100*(RHCWinCounter/numIterations) + "," + SABest + "," + avgSAScore + "," + avgSATrainingTimes + "," + 100*(SAWinCounter/numIterations) + "," + GABest + "," + avgGAScore + "," + avgGATrainingTimes + "," + 100*(GAWinCounter/numIterations) + "," + mimicBest + "," + avgMimicScore + "," + avgMimicTrainingTimes + "," + 100*(mimicWinCounter/numIterations));
//      pwtr.println("WINNER: " + "," + winner);
      pwtr.close();
        
    }
}
