import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;



public class SandwichBar
{
	public int whichOrder(String[] available, String[] orders){
		ArrayList<Set<String>> list = new ArrayList<Set<String>>();
		for (String s: orders) 
			list.add(sToSet(s));
		Set<String> able = new TreeSet<String>();
		for (String s: available)
			able.add(s);
		for (Set<String> set: list) {
			set.removeAll(able);
			if (set.size() == 0)
				return list.indexOf(set);
		}
		return -1;
	}
	
    public Set<String> sToSet(String s)
    {
        Set<String> result = new TreeSet<String>();
        String[] words = s.split("\\s+");
        for (String str : words) {
       	 result.add(str);
        }
        return result;
    }
    
    
    
}