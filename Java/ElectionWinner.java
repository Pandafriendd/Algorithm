import java.util.*;
public class ElectionWinner {
	// Complete the electionWinner function below.
    static String electionWinner(String[] votes) {
        if(votes == null || votes.length == 0) {
            return null;
        }
        
        HashMap<String, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < votes.length; i++) {
            freqMap.put(votes[i], freqMap.getOrDefault(votes[i], 0) + 1);
        }
        
        int max = Integer.MIN_VALUE;
        String res = "";
        for(Map.Entry<String, Integer>  entry : freqMap.entrySet()) {
            if(entry.getValue() > max || entry.getValue() == max && entry.getKey().compareTo(res) > 0) {  // !!!
                max = entry.getValue() ;
                res = entry.getKey();
            }
        }
        return res;
        
    }
}
