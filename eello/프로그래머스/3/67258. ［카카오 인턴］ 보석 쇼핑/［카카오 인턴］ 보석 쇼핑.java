import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
        Map<String, Integer> gemIdx = new HashMap<>();
        int idx = 0;
        for (String gem : gems) {
            if (!gemIdx.containsKey(gem)) {
                gemIdx.put(gem, idx++);
            }
        }
        
        int[] answer = {0, 100001};
        int[] gemCount = new int[idx];
        
        int left = 0, right = 0;
        gemCount[gemIdx.get(gems[0])]++;
        while (left <= right && right < gems.length) {
            if (includeAllGems(gemCount)) {
                if (right - left < answer[1] - answer[0]) {
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
                
                gemCount[gemIdx.get(gems[left++])]--;
            } else {
                ++right;
                if (right < gems.length) {
                    gemCount[gemIdx.get(gems[right])]++;
                }
            }
        }
        
        return answer;
    }
    
    private boolean includeAllGems(int[] gemCount) {
        for (int count : gemCount) {
            if (count == 0) return false;
        }
        return true;
    }
}