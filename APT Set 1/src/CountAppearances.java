
public class CountAppearances {
	public int numberTimesAppear(int number, int digit) {
        int count = 0;
		String str = "" + number;
        String[] s = str.split("");
        
        for (String t: s)
        {
        	if(t.equals("" + digit))
        		count++;
        }
        return count;
	}
}
