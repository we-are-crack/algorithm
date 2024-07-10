import java.util.*;

class Solution {
    
    private int n, answer;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        answer = dist.length + 1;
        Arrays.sort(dist);
        
        if (dist[dist.length - 1] >= n - 1) {
            return 1;
        }
        
        dfs(dist, dist.length - 1, 0, weak, 0);
        
        return answer == dist.length + 1 ? -1 : answer;
    }
    
    private void dfs(int[] dist, int idx, int sel, int[] weak, int bitmask) {
        if (answer <= sel) {
            return;
        }
        
        if (bitmask == (1 << weak.length) - 1) {
            answer = sel;
            return;
        }
        
        if (idx < 0) {
            return;
        }

        for (int i = 0; i < weak.length; i++) {
            if ((bitmask & (1 << i)) != 0) {
                continue;
            }
            
            int start = weak[i];
            int end = (start + dist[idx] + 1) % n;
            int temp = bitmask;
            while (start != end) {
                int at = indexOf(weak, start);
                start = (start + 1) % n;
                
                if (at != -1) {
                    temp |= 1 << at;
                }
            }
            
            dfs(dist, idx - 1, sel + 1, weak, temp);
        }
    }
    
    private int indexOf(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
            
            if (target < arr[i]) {
                break;
            }
        }
        return -1;
    }
}