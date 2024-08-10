class Solution {
    
    public int solution(String dirs) {
        boolean[][] visited = new boolean[121][121];
        int r = 5, c = 5;
        
        int answer = 0;
        for (char d : dirs.toCharArray()) {
            int[] dir = getDir(d);
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (!checkRange(nr, nc)) {
                continue;
            }
            
            int cur = r * 11 + c;
            int next = nr * 11 + nc;
            if (!visited[cur][next]) {
                answer++;
                visited[cur][next] = true;
                visited[next][cur] = true;
            }
            
            r = nr;
            c = nc;
        }
        
        return answer;
    }
    
    private int[] getDir(char d) {
        switch (d) {
            case 'U':
                return new int[] {-1, 0};
            case 'R':
                return new int[] {0, 1};
            case 'D':
                return new int[] {1, 0};
            default:
                return new int[] {0, -1};
        }
    }
    
    private boolean checkRange(int r, int c) {
        return 0 <= r && r <= 10 && 0 <= c && c <= 10;
    }
}