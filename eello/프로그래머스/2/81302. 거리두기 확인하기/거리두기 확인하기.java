import java.util.*;

class Solution {
    
    private static final int SIZE = 5;
    
    /*
        거리두기를 지키고 있는 경우
            - P1 과 P2 사이의 맨해튼 거리가 3 이상
            - 모든 P쌍에 대해 적용되어야 함
        
        맨해튼 거리 = P1이 P2를 만나기 위해 이동해야할 최소 칸
        
        모든 P에 대해 가장 가까운 다른 P를 만나기 위해 이동해야할 최소 칸이 3 이상인 경우 1(거리두기를 지키고 있음), 그렇지 않으면 0(누군가 거리두기를 지키지 않음)
    */
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = isValid(places[i]) ? 1 : 0;
        }
        
        return answer;
    }
    
    // 대기실에 있는 모든 응시자가 거리두기를 지키고 있으면 true 그렇지 않으면 false 리턴
    private boolean isValid(String[] place) {        
        char[][] chMap = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            chMap[i] = place[i].toCharArray();
        }
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (chMap[i][j] != 'P') {
                    continue;
                }
                
                int dist = bfs(chMap, i, j);
                
                if (dist < 3) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // (sr, sc) 위치의 P에서 가장 가까운 다른 P로 이동해야할 때 움직여야할 최소 거리 리턴
    // 만날 수 있는 다른 P가 없는 경우 Integer.MAX_VALUE 리턴
    private int bfs(char[][] place, int sr, int sc) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[SIZE][SIZE];
        
        que.add(new int[] {sr, sc, 0});
        visit[sr][sc] = true;
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0], c = cur[1], m = cur[2];
            
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (isValidRange(nr, nc) && !visit[nr][nc] && place[nr][nc] != 'X') {
                    if (place[nr][nc] == 'P') {
                        return m + 1;
                    }
                    
                    que.add(new int[] {nr, nc, m + 1});
                    visit[nr][nc] = true;
                }
            }
        }
        
        return Integer.MAX_VALUE;
    }
    
    private boolean isValidRange(int r, int c) {
        return 0 <= r && r < SIZE && 0 <= c && c < SIZE;
    }
}