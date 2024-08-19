import java.util.*;

class Solution {
    
    public int solution(int m, int n, String[] board) {
        char[][] chBoard = new char[board.length][];
        for (int r = 0; r < board.length; r++) {
            chBoard[r] = board[r].toCharArray();
        }
        
        int answer = 0;
        while (true) {
            int deleteCount = delete(chBoard);
            if (deleteCount == 0) {
                break;
            }
            
            fall(chBoard);
            answer += deleteCount;
        }
        
        return answer;
    }
    
    private int delete(char[][] board) {
        List<int[]> deleted = new ArrayList<>();
        for (int r = 0; r < board.length - 1; r++) {
            for (int c = 0; c < board[0].length - 1; c++) {
                if (board[r][c] == '0') continue;
                
                if (isDeletable(board, r, c)) {
                    deleted.add(new int[] {r, c});
                    deleted.add(new int[] {r, c + 1});
                    deleted.add(new int[] {r + 1, c});
                    deleted.add(new int[] {r + 1, c + 1});
                }
            }
        }
        
        int deleteCount = 0;
        for (int[] p : deleted) {
            int r = p[0], c = p[1];
            if (board[r][c] != '0') {
                board[r][c] = '0';
                deleteCount++;
            }
        }
        
        return deleteCount;
    }
    
    private boolean isDeletable(char[][] board, int r, int c) {
        return board[r][c] == board[r][c + 1]
            && board[r][c] == board[r + 1][c]
            && board[r][c] == board[r + 1][c + 1];
    }
    
    private void fall(char[][] board) {
        for (int c = 0; c < board[0].length; c++) {
            
            int f = 0;
            for (int r = board.length - 1; r >= 0; r--) {
                if (board[r][c] == '0') {
                    f++;
                } else if (f > 0) {
                    board[r + f][c] = board[r][c];
                    board[r][c] = '0';
                }
            }
        }
    }
}