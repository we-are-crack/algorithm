class Solution {
    
    public int solution(int[][] board, int[][] skill) {
        int n = board.length, m = board[0].length;
        int[][] acc = new int[n + 1][m + 1];
        
        for (int[] s : skill) {
            int type = s[0], ly = s[1], lx = s[2];
            int ry = s[3], rx = s[4], degree = s[5];
            
            if (type == 1) {
                degree *= -1;
            }
            
            acc[ly][lx] += degree;
            acc[ry + 1][rx + 1] += degree;
            acc[ly][rx + 1] += (degree * -1);
            acc[ry + 1][lx] += (degree * -1);
        }
        
        // row 누적합
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                acc[i][j] += acc[i][j - 1];
            }
        }
        
        // column 누적합
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                acc[i][j] += acc[i - 1][j];
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (0 < board[i][j] + acc[i][j]) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}