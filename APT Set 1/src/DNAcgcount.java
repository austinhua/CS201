
public class DNAcgcount {
	public double ratio(String dna)
    {
		double ratio = 0;
		if(dna.length() > 0)
		{
			String str = "";
			int count = 0;
				for(int i = 0; i < dna.length(); i++) 
				{
					str = dna.substring(i, i+1);
					if (str.equals("c") || str.equals("g"))
						count ++;
				}
			ratio = (double)count/dna.length();
		}
		return ratio;
    }
}
