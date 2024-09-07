import java.util.*;

class Solution {
    
    public int solution(int N, int[][] road, int K) {
        int[][] map = new int[N + 1][N + 1];
        for (int[] r : road) {
            int n1 = r[0], n2 = r[1], c = r[2];
            if (map[n1][n2] == 0 || map[n1][n2] > c) {
                map[n1][n2] = c;
                map[n2][n1] = c;
            }
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] cost = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        
        pq.offer(new int[] {1, 0});
        cost[1] = 0;
        
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int cur = node[0];
            int curCost = node[1];
            
            if (cost[cur] < curCost) {
                continue;
            }
            
            for (int next = 1; next <= N; next++) {
                if (cur == next) {
                    continue;
                }
                
                if (map[cur][next] != 0 && curCost + map[cur][next] < cost[next]) {
                    pq.offer(new int[] {next, curCost + map[cur][next]});
                    cost[next] = curCost + map[cur][next];
                }
            }
        }
                
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (cost[i] <= K) {
                answer++;
            }
        }
        
        return answer;
    }
}