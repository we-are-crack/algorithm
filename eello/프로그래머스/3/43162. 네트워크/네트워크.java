import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        
        boolean[] visited = new boolean[n];
        
        int network = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                network++;
                visit(i, visited, computers);
            }
        }
        
        return network;
    }
    
    private void visit(int start, boolean[] visited, int[][] computers) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            
            for (int next = 0; next < computers.length; next++) {
               if (computers[cur][next] == 1 && !visited[next]) {
                   stack.push(next);
                   visited[next] = true;
               } 
            }
        }
    }
}