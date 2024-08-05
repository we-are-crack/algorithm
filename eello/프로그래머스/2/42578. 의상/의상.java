import java.util.*;

class Solution {
    
    /**
     *  종류별 옷 개수에 고르지 않는 경우를 생각해 1을 더한 후 모든 옷 종류의 개수를 곱하고 모든 옷을 입지 않는 경우 1을 뺀다.
     */
    public int solution(String[][] clothes) {
        Map<String, Integer> types = new HashMap<>();
        for (String[] c : clothes) {
            types.put(c[1], types.getOrDefault(c[1], 0) + 1);
        }
        
        int answer = 1;
        Iterator<Integer> iter = types.values().iterator();
        while (iter.hasNext()) {
            answer *= iter.next() + 1; // 1은 해당 옷 종류를 고르지 않는 경우
        }
        
        return answer - 1;
    }
    
    /**
     *  모든 옷 조합을 고르는 방식을 dfs로 모두 곱하고 더한다.
     
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
    
    */
}