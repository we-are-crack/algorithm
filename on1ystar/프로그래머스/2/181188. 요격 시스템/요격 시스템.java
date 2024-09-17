import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] targets) {
        int missile = 1;
        int endTarget = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int[] target : targets) {
            pq.offer(target);
        }

        while (!pq.isEmpty()) {
            int[] target = pq.poll();

            // 발사
            if (endTarget <= target[0]) {
                endTarget = target[1];
                missile++;
            } else {
                endTarget = Math.min(endTarget, target[1]);
            }
        }
        
        return missile;
    }
}