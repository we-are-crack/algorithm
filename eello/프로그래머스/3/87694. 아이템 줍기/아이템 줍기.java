import java.util.*;

class Solution {
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102];
        int scale = 2;
        
        fill(map, rectangle, scale);
        erase(map, rectangle, scale);
        characterY *= scale;
        characterX *= scale;
        itemY *= scale;
        itemX *= scale;
        
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[101][101];
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        que.offer(new int[] {characterY, characterX, 0});
        visit[characterY][characterX] = true;
        
        int answer = 0;
        while (!que.isEmpty()) {
            int[] character = que.poll();
            if (character[0] == itemY && character[1] == itemX) {
                answer = character[2];
                break;
            }
            
            for (int[] d : dir) {
                int ny = character[0] + d[0];
                int nx = character[1] + d[1];
                
                if (map[ny][nx] == 1 && !visit[ny][nx]) {
                    que.offer(new int[] {ny, nx, character[2] + 1});
                    visit[ny][nx] = true;
                }
            }
        }
        
        return answer / scale;
    }
    
    private void fill(int[][] map, int[][] rectangle, int scale) {
        for (int[] rec : rectangle) {
            int ldx = rec[0] * scale;
            int ldy = rec[1] * scale;
            int rux = rec[2] * scale;
            int ruy = rec[3] * scale;
            for (int y = ldy; y <= ruy; y++) {
                for (int x = ldx; x <= rux; x++) {
                    map[y][x] = 1;
                }
            }
        }
    }
    
    private void erase(int[][] map, int[][] rectangle, int scale) {
        for (int[] rec : rectangle) {
            int ldx = rec[0] * scale + 1;
            int ldy = rec[1] * scale + 1;
            int rux = rec[2] * scale;
            int ruy = rec[3] * scale;
            for (int y = ldy; y < ruy; y++) {
                for (int x = ldx; x < rux; x++) {
                    map[y][x] = 0;
                }
            }
        }
    }
}