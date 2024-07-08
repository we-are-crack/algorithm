import java.util.*;

class Solution {
    
    private static final char[] DIR_ALPHA = {'d', 'l', 'r', 'u'};
    private static final int[][] DIR = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    
    private int n, m, r, c, k;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        
        String answer = dfs(x, y, k, new Stack<>());
        return answer == null ? "impossible" : answer;
    }
    
    private String dfs(int y, int x, int remain, Stack<Character> path) {
        int needMinMoveCount = Math.abs(y - r) + Math.abs(x - c);
        if (remain < needMinMoveCount || (remain - needMinMoveCount) % 2 == 1) {
            return null;
        }
        
        if (remain == 0) {
            return y == r && x == c ? makePath(path) : null;
        }
        
        for (int d = 0; d < DIR.length; d++) {
            int ny = y + DIR[d][0];
            int nx = x + DIR[d][1];
            
            if (!isValid(ny, nx)) {
                continue;
            }
            
            path.push(DIR_ALPHA[d]);
            String result = dfs(ny, nx, remain - 1, path);
            path.pop();
            
            if (result != null) {
                return result;
            }
        }
        
        return null;
    }
    
    private String makePath(Stack<Character> path) {
        StringBuilder sb = new StringBuilder();
        path.forEach(sb::append);
        return sb.toString();
    }
    
    private boolean isValid(int y, int x) {
        return 0 < y && y <= n && 0 < x && x <= m;
    }
}