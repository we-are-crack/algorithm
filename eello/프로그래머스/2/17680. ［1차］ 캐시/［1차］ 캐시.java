import java.util.*;

class Solution {
    
    public int solution(int cacheSize, String[] cities) {
        Map<String, Integer> cache = new HashMap<>();
        
        int answer = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            answer += cache.containsKey(city) ? 1 : 5;
            
            if (cacheSize == 0) continue;
            
            cache.put(city, i);
            if (cache.size() > cacheSize) {
                List<Map.Entry<String, Integer>> entry = new ArrayList<>(cache.entrySet());
                Collections.sort(entry, (a, b) -> a.getValue() - b.getValue());
                cache.remove(entry.get(0).getKey());
            }
        }
        
        return answer;
    }
}