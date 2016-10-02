
public class DNAReverse {
	public String reverse(String dna) {
		String reverse = "";
		for (int i=dna.length(); i>0; i--) {
			reverse += dna.substring(i-1, i);	
		}	
		return reverse;		
	}
	
}
