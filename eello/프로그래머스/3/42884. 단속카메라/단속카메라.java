import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a ,b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
                
            return a[1] - b[1];
        });
        
        for (int[] route : routes) {
            pq.add(route);
        }
        
        int[] prev = pq.poll();
        int answer = 1;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (prev[1] < cur[0]) {
                answer++;
                prev = cur;
            } else {
                prev[0] = cur[0];
                prev[1] = Math.min(prev[1], cur[1]);
            }
        }
        
        
        return answer;
    }
}