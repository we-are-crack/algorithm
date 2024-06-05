import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            int node1 = e[0];
            int node2 = e[1];
            
            adj[node1].add(node2);
            adj[node2].add(node1);
        }
        
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        
        que.add(1);
        visited[1] = true;
        int answer = 0;
        while (!que.isEmpty()) {
            int qSize = que.size();
            answer = qSize;
            
            for (int i = 0; i < qSize; i++) {
                int cur = que.poll();
                
                for (int next : adj[cur]) {
                    if (!visited[next]) {
                        que.add(next);
                        visited[next] = true;
                    }
                }
            }
        }
        
        return answer;
    }
}