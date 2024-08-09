import java.util.*;

class Solution {
    
    public int solution(int[] topping) {
        Map<Integer, Integer> chul = new HashMap<>();
        for (int t : topping) {
            chul.put(t, chul.getOrDefault(t, 0) + 1);
        }
        
        Set<Integer> bro = new HashSet<>();
        int answer = 0;
        for (int i = 0; i < topping.length; i++) {
            int t = topping[i];
            bro.add(t);
            
            int cnt = chul.get(t);
            if (cnt == 1) {
                chul.remove(t);
            } else chul.put(t, cnt - 1);
            
            if (bro.size() == chul.size()) {
                answer++;
            }
        }

        return answer;
    }
}