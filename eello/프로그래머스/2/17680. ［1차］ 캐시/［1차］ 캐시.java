import java.util.*;

class Solution {
    
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0)
            return cities.length * 5;
        
        List<String> cache = new LinkedList<>();
        
        int answer = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            if (cache.contains(city)) {
                answer += 1;
                cache.remove(city);
            } else {
                answer += 5;
                
                if (cache.size() == cacheSize)
                    cache.remove(0);
            }
            
            cache.add(city);
        }
        
        return answer;
    }
}