import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
        Set<String> gemSet = new HashSet<>();
        for (String gem : gems) {
            gemSet.add(gem);
        }
        
        int[] answer = {0, 100001};
        
        Map<String, Integer> gemCount = new HashMap<>();
        gemCount.put(gems[0], 1);
        int left = 0, right = 0;
        while (left <= right && right < gems.length) {
            if (gemCount.size() == gemSet.size()) {
                if (right - left < answer[1] - answer[0]) {
                    answer[0] = left + 1;
                    answer[1] = right + 1;    
                }
                
                int leftGemCount = gemCount.get(gems[left]);
                if (leftGemCount == 1) {
                    gemCount.remove(gems[left++]);
                } else {
                    gemCount.replace(gems[left++], leftGemCount - 1);
                }
            } else {
                right++;
                if (right < gems.length) {
                    gemCount.put(gems[right], gemCount.getOrDefault(gems[right], 0) + 1);
                }
            }
        }
        
        return answer;
    }
}