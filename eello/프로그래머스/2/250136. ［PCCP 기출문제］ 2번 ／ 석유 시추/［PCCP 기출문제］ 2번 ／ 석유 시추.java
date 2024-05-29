import java.util.*;

class Solution {
    
    private static class Result {
        Set<Integer> colums;
        int area;
        
        public Result(Set<Integer> colums, int area) {
            this.colums = colums;
            this.area = area;
        }
    }
    
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        int[] cache = new int[m];
        
        boolean[][] visited = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (land[r][c] == 1 && !visited[r][c]) {
                    Result res = bfs(land, visited, r, c);
                    for (int col : res.colums) {
                        cache[col] += res.area;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int val : cache) {
            answer = Math.max(answer, val);
        }
        
        return answer;
    }
    
    private Result bfs(int[][] land, boolean[][] visited, int sr, int sc) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[] {sr, sc});
        visited[sr][sc] = true;
        
        int area = 0;
        Set<Integer> colums = new HashSet<>();
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            area++;
            colums.add(cur[1]);
            
            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                
                if (checkRange(land, nr, nc) && !visited[nr][nc] && land[nr][nc] == 1) {
                    que.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return new Result(colums, area);
    }
    
    private boolean checkRange(int[][] land, int r, int c) {
        int n = land.length;
        int m = land[0].length;
        
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}