import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        
        int[] start = new int[2];
        for (int r = 0; r < maps.length; r++) {
            for (int c = 0; c < maps[0].length(); c++) {
                if (maps[r].charAt(c) == 'S') {
                    start[0] = r;
                    start[1] = c;
                }
            }
        }
        
        int[] lever = bfs(maps, start[0], start[1], 'L');
        if (lever == null) {
            return -1;
        }
        answer += lever[2];
        
        int[] end = bfs(maps, lever[0], lever[1], 'E');
        return end == null ? -1 : answer + end[2];
    }
    
    private int[] bfs(String[] maps, int sr, int sc, char dest) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[maps.length][maps[0].length()];
        
        que.offer(new int[] {sr, sc, 0});
        visit[sr][sc] = true;
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (maps[cur[0]].charAt(cur[1]) == dest) {
                return cur;
            }
            
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                
                if (checkRange(maps, nr, nc) && !visit[nr][nc] && maps[nr].charAt(nc) != 'X') {
                    visit[nr][nc] = true;
                    que.offer(new int[] {nr, nc, cur[2] + 1});
                }
            }
        }
        
        return null;
    }
    
    private boolean checkRange(String[] maps, int r, int c) {
        return 0 <= r && r < maps.length
            && 0 <= c && c < maps[0].length();
    }
}