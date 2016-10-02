
public class HuffmanDecoding {

	public String decode(String archive, String[] dictionary) {
		return "";
	}
	
	//return the ith bit of n
	//0th is the first bit (rightmost)
	public static int ithBit(int n, int i) {
		return (n >> i) & 1; //shift i digits to the right, getting rid of shifted digits and adding to the front
	}
	
	// 31 0000000011111
	//  1 0000000000001
   //31&1 0000000000001 matches each digit
	
	
	public static void printBits(int n) {
		// print just last bit
		for (int place = 31; place >- 0; place--)	
			System.out.print(ithBit(n, place));
		
		System.out.println(n % 2);
		System.out.println(n & 1); //same thing
		
		System.out.println(Integer.toBinaryString(n));
	}
	
	public static void main(String[] args) {
		printBits(15);

	}

}
