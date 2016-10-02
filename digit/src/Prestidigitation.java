import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*************************************************************************
 * Author: Austin Hua
 * Course: CompSci 201, Fall 2014 
 * Collaborators: None
 * Resources Used: None
 * Comments:
 * 
 * 
 * Known bugs:
 * 
 *************************************************************************/

public class Prestidigitation {
	public static final int MAX_DIGIT = 10;

	// chooser allows users to select a file by navigating through
	// directories
	private static JFileChooser ourChooser = new JFileChooser(System
			.getProperties().getProperty("user.dir"));

	/**
	 * Brings up chooser for user to select a file
	 * 
	 * @return Scanner for user selected file, null if file not found
	 */
	public Scanner getScanner() {

		int retval = ourChooser.showOpenDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			File f = ourChooser.getSelectedFile();
			Scanner s;
			try {
				s = new Scanner(f);
			} catch (FileNotFoundException e) {
				return null;
			}
			return s;
		}
		return null;
	}

	/**
	 * Reads all of the integers from the given file
	 * 
	 * @param sc
	 *            in legal (i.e., non-null) text file for reading
	 * @return list of integers from the file
	 */
	public ArrayList<Integer> readNumbers(Scanner sc) {
		// Todo Implement readNumbers
		ArrayList<Integer> nums = new ArrayList<Integer>();
		while (sc.hasNext()) {
			/*String s = sc.next();
			if (s.equals("(*")) {
				while(!sc.next().equals("*)"));
			}*/
			if (sc.hasNextInt())
				nums.add(sc.nextInt());
			else
				sc.next();
		}
		return nums;
	}

	/**
	 * Returns the nth highest order digit of num, i.e., the nth digit from the
	 * left. We take the leftmost digit to be the 0th. nthDigit should evaluate
	 * to 0 for digits beyond the "end" of the number. 
	 */
	public int nthDigit(int n, int num) {
		// Todo Implement nthDigit
		String strNum = String.valueOf(num);
		if (n + 1> strNum.length()) 
			return 0;
		return Integer.parseInt(strNum.substring(n, n+1));
	}

	/**
	 * Returns the [0-MAX_DIGIT] digit distribution for nums
	 * nthDigitTally([12176, 5476, 543, 3490, 24892, 28619, 2595, 603, 2527,
	 * 1465, 1858], 0) should return [0, 3, 4, 1, 0, 2, 1, 0, 0, 0] for
	 * MAX_DIGIT == 9
	 * 
	 * @param nums
	 *            non-null list of positive integers
	 * @param n
	 *            zero-indexed digit, so that 0 is the first/leftmost digit, 1
	 *            is second digit, etc.
	 * @return distribution for each digit in range [0,MAX_DIGIT]
	 */
	public int[] nthDigitTally(int n, ArrayList<Integer> nums) {
		// Todo Complete nthDigitalTally
		int[] tally = new int[MAX_DIGIT];
		for(int num : nums) {
			if (nthDigit(n, num) < MAX_DIGIT) {
				tally[nthDigit(n, num)] += 1;
			}
		}
		return tally;
	}

	public static void main(String[] args) {
		int sum = 0;
		// Todo Query user for M
		String input = JOptionPane.showInputDialog("Enter n", "0");
		int n = Integer.parseInt(input);
		// Todo Ask user to select file
		Prestidigitation a = new Prestidigitation();
		int[] tally = a.nthDigitTally(n, a.readNumbers(a.getScanner()));
		// Todo Print out digit distribution
		for (int i = 0; i < tally.length; i ++) {
			sum += tally[i];
		}
		for(int i = 0; i < MAX_DIGIT; i ++) {
			System.out.println(i + "s: " + tally[i] + " (" + Math.round(100*((double)tally[i]/sum)) + "%)");
		}

	}
}
