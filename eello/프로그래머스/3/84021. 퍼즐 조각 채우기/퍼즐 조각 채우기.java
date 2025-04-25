import java.util.*;

class Solution {
    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;
        
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                table[y][x] = table[y][x] == 0 ? 1 : 0;
            }
        }
        
        Map<Integer, List<Piece>> pieces = new HashMap<>();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (table[y][x] == 1) {
                    continue;
                }
                
                Piece piece = new Piece(bfs(table, y, x));
                int count = piece.getCount();
                if (!pieces.containsKey(count)) {
                    pieces.put(count, new ArrayList<>());
                }
                
                pieces.get(count).add(piece);
            }
        }
        
        int answer = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (game_board[y][x] == 1) {
                    continue;
                }
                
                List<int[]> piece = bfs(game_board, y, x);
                    
                List<Piece> candidate = pieces.get(piece.size());
                if (candidate == null) {
                    continue;
                }

                for (Piece p : candidate) {
                    if (p.isUsed()) {
                        continue;
                    }

                    if (p.isFit(piece)) {
                        p.use();
                        answer += p.getCount();
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
    
    private List<int[]> bfs(int[][] board, int sy, int sx) {
        List<int[]> piece = new ArrayList<>();
        Queue<int[]> que = new ArrayDeque<>();
        
        int[] start = new int[]{sy, sx};
        board[sy][sx] = 1;
        piece.add(start);
        que.add(start);
        
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!que.isEmpty()) {
            int[] curr = que.poll();
            int y = curr[0];
            int x = curr[1];
            
            board[y][x] = 1;
            
            for (int[] d : dir) {
                int ny = y + d[0];
                int nx = x + d[1];
                
                if (checkRange(board, ny, nx) && board[ny][nx] == 0) {
                    board[ny][nx] = 1;
                    
                    int[] nxt = new int[]{ny, nx};
                    que.add(nxt);
                    piece.add(nxt);
                }
            }
        }
        
        return piece;
    }
    
    private boolean checkRange(int[][] board, int r, int c) {
        int n = board.length;
        return 0 <= r && r < n && 0 <= c && c < n;
    }
    
    private static class Piece {
        
        private int count;
        private List<boolean[][]> variant = new ArrayList<>();
        private boolean use = false;
        
        public Piece(List<int[]> piece) {
            count = piece.size();
            
            move(piece);
            variant.add(marking(piece));
            
            for (int i = 0; i < 3; i++) {
                rotate(piece);
                move(piece);
                variant.add(marking(piece));
            }
        }
                        
        public boolean isFit(List<int[]> piece) {
            if (piece.size() != count) {
                return false;
            }
            
            move(piece);
            for (boolean[][] vari : variant) {
                if (isFit(vari, piece)) {
                    return true;
                }
            }
            
            return false;
        }
        
        private boolean isFit(boolean[][] vari, List<int[]> piece) {
            for (int[] p : piece) {
                int y = p[0], x = p[1];

                if (!vari[y][x]) {
                    return false;
                }
            }
            
            return true;
        }
        
        public void use() {
            use = true;
        }
        
        public int getCount() {
            return count;
        }
                        
        public boolean isUsed() {
            return use;
        }
        
        private void move(List<int[]> piece) {
            // 0, 0 기준쪽으로 도형 이동
            int minY = Integer.MAX_VALUE, minX = Integer.MAX_VALUE;
            for (int[] p : piece) {
                minY = Math.min(minY, p[0]);
                minX = Math.min(minX, p[1]);
            }
            
            for (int[] p : piece) {
                p[0] -= minY;
                p[1] -= minX;
            }
        }
        
        private void rotate(List<int[]> piece) {
            // 90도 회전 (6x6 기준으로 회전)
            for (int i = 0; i < piece.size(); i++) {
                // y = x, x = 5 - y
                int[] p = piece.get(i);
                
                int temp = p[0];
                p[0] = p[1];
                p[1] = 5 - temp;
            }
        }
        
        private boolean[][] marking(List<int[]> piece) {
            boolean[][] v = new boolean[6][6];
            for (int[] p : piece) {
                v[p[0]][p[1]] = true;
            }
            
            return v;
        }
    }
}