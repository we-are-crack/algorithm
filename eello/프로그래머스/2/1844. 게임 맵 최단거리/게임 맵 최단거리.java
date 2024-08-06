import java.util.*;

class Solution {
    
    public int solution(int[][] maps) {
        int r = maps.length, c = maps[0].length;
        
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[r][c];
        
        que.add(new int[] {0, 0, 1});
        visit[0][0] = true;
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            
            if (cur[0] == r - 1 && cur[1] == c - 1) {
                return cur[2];
            }
            
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                
                if (0 <= nr && nr < r && 0 <= nc && nc < c && maps[nr][nc] == 1 && !visit[nr][nc]) {
                    visit[nr][nc] = true;
                    que.offer(new int[] {nr, nc, cur[2] + 1});
                }
            }
        }
        
        return -1;
    }
}