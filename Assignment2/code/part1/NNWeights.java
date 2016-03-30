//package opt.assignment2;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import opt.OptimizationAlgorithm;
import opt.RandomizedHillClimbing;
import opt.SimulatedAnnealing;
import opt.example.NeuralNetworkOptimizationProblem;
import opt.ga.StandardGeneticAlgorithm;
import shared.ConvergenceTrainer;
import shared.DataSet;
import shared.ErrorMeasure;
import shared.Instance;
import shared.SumOfSquaresError;
import util.linalg.DenseVector;
import util.linalg.Vector;
import func.nn.backprop.BackPropagationNetwork;
import func.nn.backprop.BackPropagationNetworkFactory;
import func.nn.backprop.BatchBackPropagationTrainer;
import func.nn.backprop.RPROPUpdateRule;
//import Assignment2.CSVfile;

public class NNWeights {
	
	private static final int[] layers = {6, 4, 2, 1};
	private static final String[] names = {"BackProp", "RHC", "SA", "GA"};

	private static final int trainingIterations[] = {1000, 1000, 10}; //RHC, SA, GA

	
    public static void main(String[] args) {
    	
    	// load data
    	CSVfile testLabelsFile = new CSVfile("/Users/craiglab/Downloads/ABAGAIL-master/src/opt/assignment2/test_label.csv");
    	CSVfile testFeaturesFile = new CSVfile("/Users/craiglab/Downloads/ABAGAIL-master/src/opt/assignment2/test_feature.csv");
    	CSVfile trainLabelsFile = new CSVfile("/Users/craiglab/Downloads/ABAGAIL-master/src/opt/assignment2/train_label.csv");
    	CSVfile trainFeaturesFile = new CSVfile("/Users/craiglab/Downloads/ABAGAIL-master/src/opt/assignment2/train_feature.csv");
  
    	List<ArrayList<Double>> testLabels = testLabelsFile.read();
    	List<ArrayList<Double>> testFeatures = testFeaturesFile.read();
    	List<ArrayList<Double>> trainLabels = trainLabelsFile.read();
    	List<ArrayList<Double>> trainFeatures = trainFeaturesFile.read();
       	System.out.println("Loaded " + trainLabels.size() + " train samples and " + testLabels.size() + " test samples");

        // initialize instance structures
        Instance[] patterns = new Instance[trainLabels.size()];
        for (int i = 0; i < patterns.length; i++) {
            double[] trainFeaturesArray = convertDoubleListToArray(trainFeatures.get(i));
            double[] trainLabelsArray = convertDoubleListToArray(trainLabels.get(i));
        	patterns[i] = new Instance(trainFeaturesArray);
            patterns[i].setLabel(new Instance(trainLabelsArray));
        }
        //System.out.println("first part success!");
        System.out.println(patterns[patterns.length - 1]);

        DataSet set = new DataSet(patterns);
        
        // create networks (1 backprop, 3 optimization)
        long start = System.nanoTime();
        BackPropagationNetworkFactory factory = new BackPropagationNetworkFactory();
        BackPropagationNetwork networks[] = new BackPropagationNetwork[4];                
        OptimizationAlgorithm[] oa = new OptimizationAlgorithm[3];
        NeuralNetworkOptimizationProblem[] nnop = new NeuralNetworkOptimizationProblem[3];
        ErrorMeasure measure = new SumOfSquaresError();
        for(int i = 0; i < networks.length; i++) {
            networks[i] = factory.createClassificationNetwork(layers);
        }
        nnop[0] = new NeuralNetworkOptimizationProblem(set, networks[1], measure);
        nnop[1] = new NeuralNetworkOptimizationProblem(set, networks[2], measure);
        nnop[2] = new NeuralNetworkOptimizationProblem(set, networks[3], measure);
        oa[0] = new RandomizedHillClimbing(nnop[0]);
        oa[1] = new SimulatedAnnealing(1E6, .90, nnop[1]);
        oa[2] = new StandardGeneticAlgorithm(200, 100, 10, nnop[2]);
        System.out.println("Created networks in " + (System.nanoTime() - start) / 1E9 + " seconds");
        //System.out.println("second part success!");


        // train network via back propagation
        start = System.nanoTime();
        ConvergenceTrainer trainer = new ConvergenceTrainer(
        							 new BatchBackPropagationTrainer(
        							 set, 
        							 networks[0],
        							 new SumOfSquaresError(), 
        							 new RPROPUpdateRule()));
        trainer.train();
        System.out.println("Trained " + names[0] + " in " + (System.nanoTime() - start) / 1E9 + " seconds");
        
        // train optimization algorithms
        for(int i = 0; i < oa.length; i++) {
        	start = System.nanoTime();
			for(int j = 0; j < trainingIterations[i]; j++) {
                oa[i].train();
            }         
            Instance optimalInstance = oa[i].getOptimal();
            networks[i].setWeights(optimalInstance.getData());
            System.out.println("Trained " + names[i] + " in " + (System.nanoTime() - start) / 1E9 + " seconds");
        }
                
        // run the networks over the test features
    	List<ArrayList<Double>> results = new ArrayList<ArrayList<Double>>();
    	for (int i = 0; i < networks.length; i++) {
    		results.add(new ArrayList<Double>());
	        for (int j = 0; j < testLabels.size(); j++) {
	        	networks[i].setInputValues(convertDoubleListToVector(testFeatures.get(j)));
	            networks[i].run();
	            results.get(i).add(networks[i].getOutputValues().get(0));
	        }
	        evaluateResults(names[i], results.get(i), testLabels);
    	}
    }
    
    // convert an ArrayList<Double> to a double[]    
    public static double[] convertDoubleListToArray(List<Double> list) {
        double[] ret = new double[list.size()];
        Iterator<Double> iterator = list.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
    
    // convert an ArrayList<Double> to a util.linalg.Vector
    public static Vector convertDoubleListToVector(List<Double> list) {
		return new DenseVector(convertDoubleListToArray(list));
    }
    
    // convert util.linalg.Vector to ArrayList<Double>
    public static List<Double> convertVectorToDoubleList(Vector vector) {
    	List<Double> ret = new ArrayList<Double>();
        for (int i = 0; i < vector.size(); i++) {
            ret.add(vector.get(i));
        }
    	return ret;
    }
    
    // calculate statistics on the accuracy of the classifiers
    public static void evaluateResults (String name, List<Double> results, List<ArrayList<Double>> labels) {
    	for (double threshold = 0.4; threshold < 0.5; threshold += 0.1) {
	    	int tp = 0, tn = 0, fp = 0, fn = 0, pos = 0, neg = 0;	
    		for (int i = 0; i < results.size(); i++) {
	    		boolean p = results.get(i) < threshold ? false : true;
	    		boolean a = labels.get(i).get(0) < 0.5 ? false : true;
	    		if (p && a) {
	    			tp++;
	    		} else if (!p && !a) { 
	    			tn++;
	    		} else if (!p && a) {
	    			fn++;
	    		} else {
	    			fp++;
	    		}
	    		if (a) {
	    			pos++;
	    		} else {
	    			neg++;
	    		}
	    	}
    	    float tpr = (float) tp / pos;
    	    float fpr = (float) fp / neg;
    	    float accuracy = (float) (tp + tn) / (tp + tn + fp + fn);
    	    float precision = (float) tp / (tp + fp);
    	    float recall = (float) tp / (tp + fn);
    	    float F = (float) (2.0 * (precision * recall) / (precision + recall));
    	    System.out.println("--- " + name + " ---");
    	    System.out.println("Threshold: " + threshold);
    	    System.out.println("  True Positive Rate: " + tpr + " False Positive Rate: " + fpr);
    	    System.out.println("  Accuracy: " + accuracy + " Recall: " + recall + " Precision: " + precision + " F1: " + F);
    	}
    }
}