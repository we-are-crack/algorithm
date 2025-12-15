import java.util.*;

class Solution {
    
    private final char WALL = 'D';
    
    private String[] board;
    private int r, c;
    private int[] start, dest;
    private int[][] visit;
    
    private void init(String[] board) {
        this.board = board;
        r = board.length; // 세로 길이
        c = board[0].length(); // 가로 길이
        
        visit = new int[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }
        
        // 시작, 종료지점 저장
        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                char elem = board[y].charAt(x);
                if (elem == 'R') {
                    start = new int[] {y, x};
                } else if (elem == 'G') {
                    dest = new int[] {y, x};
                }
            }
        }
    }
    
    public int solution(String[] board) {
        init(board);
        
        // {y좌표, x좌표, 움직인 횟수} 저장
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[] {start[0], start[1], 0});
        
        int answer = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            
            if (cur[0] == dest[0] && cur[1] == dest[1]) {
                answer = Math.min(answer, cur[2]);
                continue;
            }
            
            for (int d = 0; d < 4; d++) {
                int[] nxt = getNextPosition(cur[0], cur[1], d);
                int ny = nxt[0], nx = nxt[1];
                
                // d 방향으로 이동했을 때 바로 다음 칸이 벽이거나 가장자리인 경우
                // 현재 위치 그대로 리턴하므로 제자리인 경우
                if (cur[0] == ny && cur[1] == nx) {
                    continue;
                }
                
                int nc = cur[2] + 1;
                if (nc < visit[ny][nx]) {
                    que.add(new int[] {ny, nx, nc});
                    visit[ny][nx] = nc;
                }
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    // start(y, x)에서 d 방향으로 출발해 도착하는 마지막 칸의 좌표 리턴
    private int[] getNextPosition(int y, int x, int d) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        while (true) {
            int ny = y + dir[d][0];
            int nx = x + dir[d][1];
            
            if (!isValid(ny, nx) || board[ny].charAt(nx) == WALL) {
                break;
            }
            
            y = ny;
            x = nx;
        }
        
        return new int[] {y, x};
    }
    
    private boolean isValid(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}