import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }
        
        while (!pq.isEmpty() && n > 0) {
            int work = pq.poll();
            work -= 1;
            
            if (work > 0) {
                pq.add(work);
            }
            
            n--;
        }
        
        long answer = 0;
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += work * work;
        }
        
        return answer;
    }
}