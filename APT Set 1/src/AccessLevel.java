
public class AccessLevel {
	public String canAccess(int[] rights, int minPermission) {
        String result = "";
        for(int n : rights) {
        	if (n < minPermission)
        		result += "D";
        	else
        		result += "A";
        }
        return result;
	}
}
