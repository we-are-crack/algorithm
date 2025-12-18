import java.util.*;

class Solution {
    
    public int[] solution(String[] maps) {
        int r = maps.length;
        int c = maps[0].length();
        int[][] map = new int[r][c];
        
        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                char elem = maps[y].charAt(x);
                
                if (elem == 'X') map[y][x] = 0;
                else map[y][x] = elem - '0';
            }
        }
        
        List<Integer> answerList = new ArrayList<>();
        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                if (map[y][x] > 0) {
                    answerList.add(bfs(map, y, x));
                }
            }
        }
        
        if (answerList.isEmpty()) {
            return new int[] {-1};
        }
        
        answerList.sort(Comparator.naturalOrder());
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
    
    private int bfs(int[][] map, int sr, int sc) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[] {sr, sc});
        
        int day = map[sr][sc];
        map[sr][sc] = 0;
        
        while (!que.isEmpty()) {
            int[] curr = que.poll();
            
            for (int[] d : dir) {
                int nr = curr[0] + d[0];
                int nc = curr[1] + d[1];
                
                if (isValidRange(map, nr, nc) && 0 < map[nr][nc]) {
                    day += map[nr][nc];
                    map[nr][nc] = 0;
                    que.add(new int[] {nr, nc});
                }
            }
        }
        
        return day;
    }
    
    private boolean isValidRange(int[][] map, int y, int x) {
        int r = map.length;
        int c = map[0].length;
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}