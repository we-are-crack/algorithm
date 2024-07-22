import java.util.*;

class Solution {
    
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int size : tangerine) {
            count.put(size, count.getOrDefault(size, 0) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> entrySet = new ArrayList<>(count.entrySet());
        Collections.sort(entrySet, Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
        int answer = 0;
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            answer++;
            k -= entry.getValue();
            
            if (k <= 0) {
                break;
            }
        }
        
        return answer;
    }
}