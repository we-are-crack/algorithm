import java.util.*;

class Solution {
    
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        
        Queue<Integer> que1 = new ArrayDeque<>();
        Queue<Integer> que2 = new ArrayDeque<>();
        long sum1 = 0, sum2 = 0;
        
        for (int i = 0; i < n; i++) {
            que1.offer(queue1[i]);
            sum1 += queue1[i];
            
            que2.offer(queue2[i]);
            sum2 += queue2[i];
        }
        
        if ((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        
        int answer = 0;
        while (sum1 != sum2) {
            if (answer > 3 * n || que1.isEmpty() || que2.isEmpty()) {
                return -1;
            }
            
            if (sum1 > sum2) {
                int pop = que1.poll();
                sum1 -= pop;
                sum2 += pop;
                que2.offer(pop);
            } else {
                int pop = que2.poll();
                sum2 -= pop;
                sum1 += pop;
                que1.offer(pop);
            }
            
            answer++;
        }
        
        return answer;
    }
}