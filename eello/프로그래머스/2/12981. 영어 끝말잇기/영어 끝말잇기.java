import java.util.*;

class Solution {
    
    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        
        for (int i = 1; i < words.length; i++) {
            if (!isPossible(set, words[i - 1], words[i])) {
                return new int[] {i % n + 1, i / n + 1};
            }
            set.add(words[i]);
        }

        return new int[] {0, 0};
    }
    
    private boolean isPossible(Set<String> set, String pre, String cur) {
        if (set.contains(cur)) {
            return false;
        }
        
        return pre.charAt(pre.length() - 1) == cur.charAt(0);
    }
}