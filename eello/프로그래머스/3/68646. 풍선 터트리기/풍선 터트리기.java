import java.util.*;

class Solution {
    
    /**
     *  선택한 풍선의 번호가 왼쪽 또는 오른쪽 풍선들의 최소 번호보다 한 번만 작으면 살아남을 수 있는 풍선이됨.
     *  left 또는 right의 값이 a[i] 또는 a[n - i - 1]의 원소로 갱신되었다는 것은
     *  왼쪽 또는 오른쪽 풍선들보다 작은 번호이기 때문에 최소 한 번은 작으므로 살아남을 수 있는 풍선.
     */
    
    public int solution(int[] a) {
        Set<Integer> set = new HashSet<>();
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        
        int n = a.length;
        for (int i = 0; i < n; i++) {
            left = Math.min(left, a[i]);
            right = Math.min(right, a[n - i - 1]);
            
            set.add(left);
            set.add(right);
        }
        
        return set.size();
    }
}