import java.util.*;

class Solution {

    public int solution(int[] players, int m, int k) {
        int answer = 0, server = 0;
        Queue<int[]> que = new ArrayDeque<>();
        
        for (int t = 0; t < players.length; t++) {
            if (!que.isEmpty() && que.peek()[0] == t) {
                server -= que.poll()[1];
            }
            
            int player = players[t];
            if (player < server * m) {
                continue;
            }
            
            int need = (player - server * m) / m;
            answer += need;
            server += need;
            que.add(new int[]{t + k, need});
        }
        
        return answer;
    }
}