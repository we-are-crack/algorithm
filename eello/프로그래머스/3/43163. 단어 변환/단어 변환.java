import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int n = words.length;
        Queue<int[]> que = new ArrayDeque<>();
        boolean[] visit = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (isConvertable(begin, words[i])) {
                visit[i] = true;
                que.add(new int[] {i, 1});
            }
        }
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            String curWord = words[cur[0]];
            int convertCount = cur[1];
            
            if (curWord.equals(target)) {
                return convertCount;
            }
            
            for (int next = 0; next < n; next++) {
                if (!visit[next] && isConvertable(curWord, words[next])) {
                    visit[next] = true;
                    que.add(new int[] {next, convertCount + 1});
                }
            }
        }
        
        return 0;
    }
    
    private boolean isConvertable(String word, String target) {
        int diff = 0;
        int len = word.length();
        
        for (int i = 0; i < len; i++) {
            if (word.charAt(i) == target.charAt(i)) {
                continue;
            }
            
            if (diff > 0) {
                return false;
            }
            
            diff++;
        }
        
        return true;
    }
}