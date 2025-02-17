import java.util.*;

class Solution {
    
    private final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    private int n, m;
    private String[] storage;
    private boolean[][] accessible, removed;
    
    public int solution(String[] storage, String[] requests) {
        init(storage);
        
        int answer = n * m;
        for (String req : requests) {
            if (req.length() == 1) { // 지게차
                answer -= removeByLift(req.charAt(0));
            } else { // 크레인
                Set<Character> targets = new HashSet<>();
                for (char ch : req.toCharArray()) {
                    targets.add(ch);
                }
                
                answer -= removeByCrane(targets);
            }
            
            updateAccessible();
        }
        
        return answer;
    }
    
    private void init(String[] storage) {
        n = storage.length;
        m = storage[0].length();
        
        this.storage = storage;
        
        accessible = new boolean[n][m];
        removed = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    accessible[i][j] = true;
                }
            }
        }
    }
    
    private int removeByLift(char target) { // 지게차로 물품 꺼냄
        int rm = 0;
        List<int[]> ac = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (removed[r][c] || storage[r].charAt(c) != target || !accessible[r][c]) {
                    // 삭제되었거나, 타겟이 아니거나, 접근이 불가능하면
                    continue;
                }

                rm++;
                removed[r][c] = true;
            }
        }
        
        return rm;
    }
    
    private int removeByCrane(Set<Character> targets) { // 크레인으로 물품 꺼냄
        int rm = 0;
        List<int[]> ac = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (removed[r][c] || !targets.contains(storage[r].charAt(c))) {
                    // 삭제되었거나, 타겟이 아니면
                    continue;
                }
                
                rm++;
                removed[r][c] = true;
            }
        }
                
        return rm;
    }
    
    private void updateAccessible() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (removed[r][c] && accessible[r][c]) {
                    q.offer(new int[] {r, c});
                    visit[r][c] = true;
                }
            }
        }
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (!checkRange(nr, nc) || visit[nr][nc]) {
                    continue;
                }
                
                if (removed[nr][nc]) {
                    q.offer(new int[] {nr, nc});
                    visit[nr][nc] = true;
                } else {
                    accessible[nr][nc] = true;
                }
            }
        }
    }
    
    private boolean checkRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}