import java.util.*;

class Solution {
    
    public int solution(int distance, int[] rocks, int n) {
        rocks = Arrays.copyOf(rocks, rocks.length + 1);
        rocks[rocks.length - 1] = distance;
        Arrays.sort(rocks);
        
        int answer = 0;
        int s = 0, e = distance;
        while (s <= e) {
            int m = (s + e) / 2;
            
            if (isPossible(rocks, n, m)) {
                s = m + 1;
                answer = m;
            } else {
                e = m - 1;
            }
        }
        
        return answer;
    }
    
    private boolean isPossible(int[] rocks, int n, int val) {
        int prev = 0;
        int remove = 0;
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - prev < val) {
                remove++;
            } else prev = rocks[i];
            
            if (remove > n) {
                return false;
            }
        }
        
        return true;
    }
}