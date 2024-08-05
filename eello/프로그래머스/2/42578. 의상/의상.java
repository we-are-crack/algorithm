import java.util.*;

class Solution {
    
    private int hap = -1;
    
    public int solution(String[][] clothes) {
        Map<String, Integer> types = new HashMap<>();
        for (String[] c : clothes) {
            types.put(c[1], types.getOrDefault(c[1], 0) + 1);
        }
        
        Integer[] values = types.values().toArray(new Integer[0]);
        rec(values, 0, 1);
        
        return hap;
    }
    
    private void rec(Integer[] types, int idx, int k) {
        hap += k;
        for (int i = idx; i < types.length; i++) {
            rec(types, i + 1, k * types[i]);
        }
    }
}