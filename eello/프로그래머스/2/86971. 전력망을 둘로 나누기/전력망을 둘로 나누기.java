import java.util.*;

class Solution {
    
    public int solution(int n, int[][] wires) {
        boolean[][] connect = new boolean[n + 1][n + 1];
        for (int[] w : wires) {
            connect[w[0]][w[1]] = true;
            connect[w[1]][w[0]] = true;
        }
        
        int answer = n;
        for (int[] w : wires) {
            connect[w[0]][w[1]] = false;
            connect[w[1]][w[0]] = false;
            
            int count = network(connect);
            answer = Math.min(answer, Math.abs(n - 2 * count));
            
            connect[w[0]][w[1]] = true;
            connect[w[1]][w[0]] = true;
        }
        
        return answer;
    }
    
    private int network(boolean[][] connect) {
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visit = new boolean[connect.length];
        
        que.offer(1);
        visit[1] = true;
        
        int count = 0;
        while (!que.isEmpty()) {
            count++;
            int tower = que.poll();
            
            for (int i = 1; i < connect.length; i++) {
                if (connect[tower][i] && !visit[i]) {
                    que.offer(i);
                    visit[i] = true;
                }
            }
        }
        
        return count;
    }
}