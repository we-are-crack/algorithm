import java.util.*;

class Solution {
    
    private static final int MAX_VALUE = 100_000 * 200;
    
    /**
     *  다익스트라 풀이
     */
    public int solution(int n, int s, int a, int b, int[][] fares) {        
        List<int[]>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] fare : fares) {
            adj[fare[0]].add(new int[] {fare[1], fare[2]});
            adj[fare[1]].add(new int[] {fare[0], fare[2]});
        }
        
        int[][] costs = new int[n + 1][n + 1];
        costs[s] = dijkstra(s, adj);
        costs[a] = dijkstra(a, adj);
        costs[b] = dijkstra(b, adj);
        
        int answer = costs[s][a] + costs[s][b];
        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer, costs[s][k] + costs[a][k] + costs[b][k]);
        }
        
        return answer;
    }

    private int[] dijkstra(int start, List<int[]>[] adj) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] cost = new int[adj.length + 1];
        Arrays.fill(cost, MAX_VALUE);
        
        pq.offer(new int[] {start, 0});
        cost[start] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (cur[1] > cost[cur[0]]) {
                continue;
            }
            
            for (int[] next : adj[cur[0]]) {
                int nCost = next[1] + cur[1];
                if (nCost <= cost[next[0]]) {
                    pq.offer(new int[] {next[0], nCost});
                    cost[next[0]] = nCost;
                }
            }
        }
        
        return cost;
    }
    
    /**
     *  플로이드-워셜 풀이
     
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] cost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cost[i][j] = MAX_VALUE;
            }
            cost[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            cost[fare[0]][fare[1]] = fare[2];
            cost[fare[1]][fare[0]] = fare[2];
        }
        
        floydWarshall(n, cost);
        
        int answer = cost[s][a] + cost[s][b];
        for (int k = 1; k <= n; k++) {
            answer = Math.min(answer, cost[s][k] + cost[k][a] + cost[k][b]);
        }
        
        return answer;
    }
    
    private void floydWarshall(int n, int[][] cost) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }
    }
    
    */
}