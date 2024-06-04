import java.util.*;

class Solution {
    
    public int solution(int n, int[][] costs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int[] cost : costs) {
            pq.add(cost);
        }
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int unionCount = 0;
        int answer = 0;
        while (unionCount != n - 1) {
            int[] info = pq.poll();
            int node1 = info[0], node2 = info[1], cost = info[2];
            
            if (union(parent, node1, node2)) {
                unionCount++;
                answer += cost;
            }
        }
        
        return answer;
    }
    
    private boolean union(int[] parent, int node1, int node2) {
        int node1Parent = find(parent, node1);
        int node2Parent = find(parent, node2);
        
        if (node1Parent != node2Parent) {
            if (node1Parent > node2Parent) {
                parent[node1Parent] = node2Parent;
            } else parent[node2Parent] = node1Parent;
            
            return true;
        }
        
        return false;
    }
    
    private int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent, parent[node]);
    }
}