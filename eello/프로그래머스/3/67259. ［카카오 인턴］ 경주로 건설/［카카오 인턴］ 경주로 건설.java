import java.util.*;

class Solution {
    
    public int solution(int[][] board) {
        int n = board.length;
        int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // U, R, D, L
        
        Queue<int[]> que = new ArrayDeque<>(); // elem = {r, c, d, cost}        
        int[][][] costs = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(costs[i][j], Integer.MAX_VALUE);
            }
        }
        
        que.add(new int[] {0, 0, 1, 0}); // → 방향으로 출발
        que.add(new int[] {0, 0, 2, 0}); // ↓ 방향으로 출발
        Arrays.fill(costs[0][0], 0);
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            
            for (int d = 0; d < 4; d++) {
                if (cur[2] != d && cur[2] % 2 == d % 2) { // 반대 방향인경우
                    continue;
                }
                
                int nr = cur[0] + dir[d][0];
                int nc = cur[1] + dir[d][1];
                int nCost = cur[3] + 100 + (cur[2] != d ? 500 : 0);
                if (checkRange(n, nr, nc) && board[nr][nc] == 0 && nCost <= costs[nr][nc][d]) {
                    que.add(new int[] {nr, nc, d, nCost});
                    costs[nr][nc][d] = nCost;
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, costs[n - 1][n - 1][i]);
        }
        
        return answer;
    }
    
    private boolean checkRange(int n, int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}