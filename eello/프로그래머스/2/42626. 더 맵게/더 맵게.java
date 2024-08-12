import java.util.*;

class Solution {
    
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer((long) s);
        }
        
        int mixCount = 0;
        while (pq.size() > 1 && pq.peek() < K) {           
            long mix = pq.poll() + (pq.poll() * 2);
            pq.offer(mix);
            mixCount++;
        }
        
        return pq.peek() >= K ? mixCount : -1;
    }
}