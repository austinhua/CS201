import javax.swing.JOptionPane;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import princeton.*; 

/**
 * Print statistics on Percolation: prompts the user for N and T, performs T
 * independent experiments on an N-by-N grid, prints out the 95% confidence
 * interval for the percolation threshold, and prints mean and std. deviation
 * of timings
 * 
 * @author Kevin Wayne
 * @author Jeff Forbes
 */

public class PercolationStats {
    public static int RANDOM_SEED = 1234; 
    public static Random ourRandom = new Random(RANDOM_SEED);
    
	public static void main(String[] args) {
		int N, T;
		if (args.length == 2) { // use command-line arguments for testing/grading
			N = Integer.parseInt(args[0]);
			T = Integer.parseInt(args[1]);
		} else {
			String input = JOptionPane.showInputDialog("Enter N and T",
					"20 100");
			String[] inputs = input.split("\\s+");
			N = Integer.parseInt(inputs[0]);
			T = Integer.parseInt(inputs[1]);
		}
		double[] thresholds = new double[T];
		double[] times = new double[T];
		List<Point> blockedSites = new ArrayList<>();
		for (int row = 0; row < N; row++)
			for (int col = 0; col < N; col++)
				blockedSites.add(new Point(row, col));
		
		for (int i = 0; i < T; i ++) {
			int count = 1;			
			IPercolate perc = new PercolationDFS(N);
			//IPercolate perc = new PercolationUF(N, new QuickFind());
			//IPercolate perc = new PercolationUF(N, new QuickUWPC());
			Collections.shuffle(blockedSites, ourRandom);
			
			double start = System.currentTimeMillis();
			while (!perc.percolates()) {
				Point pick = blockedSites.get(count - 1);
				perc.open(pick.x, pick.y);
				count ++;
			}
			double end = System.currentTimeMillis();
			thresholds[i] = ((double)count)/(N*N);
			times[i] = (end-start)/1000;
		}
		
		double meanThreshold = mean(thresholds);
		double meanTime = mean(times);
		
		//print results
		System.out.printf("mean percolation threshold = \t%.4f\n", meanThreshold);
		System.out.printf("stddev =\t\t\t%.4f\n", stdDev(thresholds));
		System.out.printf("95 percent conf interval =\t[%.4f, %.4f]\n\n" , 
				(meanThreshold - 1.96 * stdDev(thresholds)/Math.sqrt(T)), (meanThreshold + 1.96 * stdDev(thresholds)/Math.sqrt(T)));

		System.out.printf("total time = \t\t\t%.4fs\n", meanTime*T);
		System.out.printf("mean time per experiment = \t%.4fs\n", meanTime);
		System.out.printf("stddev = \t\t\t%.4f", stdDev(times));

	}
	
	public static double stdDev(double[] values) {
		if (values.length <= 1) return Double.NaN;
		double mean = mean(values);
		double stdDevSquared = 0;
		for (int i = 0; i < values.length; i++) {
			stdDevSquared += Math.pow((values[i] - mean),2)/(values.length - 1);
		}
		return Math.sqrt(stdDevSquared);
	}
	
	public static double mean(double[] values) {
		double sum = 0;
		for (double value : values)
			sum += value;
		return sum/values.length;
	}
}
