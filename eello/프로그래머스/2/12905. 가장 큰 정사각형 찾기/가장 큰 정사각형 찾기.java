class Solution {
    
    /*
        board[y][x] = (y, x)를 오른쪽 하단 좌표로 하는 정사각형의 한 변의 길이
        board[y][x] = min(board[y - 1][x - 1], board[y - 1][x], board[y][x - 1]) + 1
    */
    
    public int solution(int[][] board) {
        int n = board.length; // 세로 길이
        int m = board[0].length; // 가로 길이
        
        int answer = 0;
        for(int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (y == 0 || x == 0) {
                    answer = Math.max(answer, board[y][x]);
                    continue;
                }
                
                if (board[y][x] == 0) {
                    continue;
                }
                
                int min = Math.min(board[y - 1][x - 1], board[y - 1][x]);
                min = Math.min(min, board[y][x - 1]);
                
                board[y][x] = min + 1;
                answer = Math.max(answer, board[y][x] * board[y][x]);
            }
        }
        
        return answer;
    }
}