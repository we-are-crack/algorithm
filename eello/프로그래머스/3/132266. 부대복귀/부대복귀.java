import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            adj[road[0]].add(road[1]);
            adj[road[1]].add(road[0]);
        }
        
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {destination, 0});
        costs[destination] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (costs[cur[0]] < cur[1]) {
                continue;
            }
            
            for (int next : adj[cur[0]]) {
                if (cur[1] + 1 < costs[next]) {
                    costs[next] = cur[1] + 1;
                    pq.offer(new int[] {next, costs[next]});
                }
            }
        }
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            int source = sources[i];
            int cost = costs[source];
            
            answer[i] = cost == Integer.MAX_VALUE ? -1 : cost;
        }
        
        return answer;
    }
}