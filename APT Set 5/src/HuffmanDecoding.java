


public class HuffmanDecoding {
      public String decode(String archive, String[] dictionary) {
          String result = "";
          while (archive.length() > 0) {
          	for (int k = 0; k < dictionary.length; k++) {
	    	  	if (archive.startsWith(dictionary[k])) {
	    	  		result += (char)('A' + k);
	    	  		archive = archive.substring(dictionary[k].length(), archive.length());
	    	  	}
          	}
          }
          return result;
          }
   }