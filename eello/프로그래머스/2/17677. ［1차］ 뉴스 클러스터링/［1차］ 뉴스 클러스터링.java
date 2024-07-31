import java.util.*;

class Solution {
    
    private Set<String> set = new HashSet<>();
    
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = make(str1);
        Map<String, Integer> map2 = make(str2);
        
        int gyo = 0, hap = 0;
        for (String key : set) {
            int cnt1 = map1.getOrDefault(key, 0);
            int cnt2 = map2.getOrDefault(key, 0);
            
            gyo += Math.min(cnt1, cnt2);
            hap += Math.max(cnt1, cnt2);
        }
        
        double similarity = hap == 0 ? 1 : (double) gyo / hap;
        return (int) (similarity * 65536);
    }
    
    private Map<String, Integer> make(String str) {
        Map<String, Integer> map = new HashMap<>();
        str = str.toLowerCase();
        
        char prev = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (isAlpha(prev) && isAlpha(cur)) {
                String elem = "" + prev + cur;
                map.put(elem, map.getOrDefault(elem, 0) + 1);
                set.add(elem);
            }
            
            prev = cur;
        }
        
        return map;
    }
    
    private boolean isAlpha(char ch) {
        return 'a' <= ch && ch <= 'z';
    }
}