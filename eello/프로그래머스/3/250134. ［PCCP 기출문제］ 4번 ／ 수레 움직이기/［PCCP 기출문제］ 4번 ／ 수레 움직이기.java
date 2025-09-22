import java.util.*;

class Solution {
    
    private static final int MAX = Integer.MAX_VALUE;
    
    public int solution(int[][] maze) {
        Cart red = new Cart(maze), blue = new Cart(maze);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                switch (maze[i][j]) {
                    case 1:
                        red.setPos(new Position(i, j));
                        break;
                    case 2:
                        blue.setPos(new Position(i, j));
                        break;
                    case 3:
                        red.dest = new Position(i, j);
                        break;
                    case 4:
                        blue.dest = new Position(i, j);
                        break;
                    default:
                        break;
                }
            }
        }
        
        int answer = move(red, blue, 0);
        return answer == MAX ? 0 : answer;
    }
    
    public int move(Cart red, Cart blue, int count) {
        if (red.isArrival() && blue.isArrival()) {
            return count;
        }
        
        List<Position> redNexts = red.getNexts();
        List<Position> blueNexts = blue.getNexts();
        
        if (redNexts.isEmpty() || blueNexts.isEmpty()) {
            return MAX;
        }
        
        int ret = MAX;
        for (Position rn : redNexts) {
            Cart cr = red.copy();
            cr.setPos(rn);
            
            for (Position bn : blueNexts) {
                if (rn.equals(blue.pos) && bn.equals(red.pos)) {
                    // 두 수레가 서로 교차해 움직이는 경우
                    continue;
                }
                
                if (rn.equals(bn)) {
                    // 두 수레가 같은 칸으로 움직이는 경우
                    continue;
                }
                
                
                Cart cb = blue.copy();
                cb.setPos(bn);
                
                ret = Math.min(ret, move(cr, cb, count + 1));
            }
        }
        
        return ret;
    }
    
    private static class Cart {
        Position pos;
        Position dest;
        int[][] maze;
        boolean[][] visit;
        int n, m;
        
        private Cart() {}
                
        public Cart(int[][] maze) {
            this.maze = maze;
            this.n = maze.length;
            this.m = maze[0].length;
            this.visit = new boolean[n][m];
        }
        
        public void setPos(Position pos) {
            this.pos = pos;
            visit[pos.y][pos.x] = true;
        }
        
        public void setDest(Position dest) {
            this.dest = dest;
        }
        
        public boolean isArrival() {
            return pos.equals(dest);
        }
        
        public List<Position> getNexts() {
            List<Position> nexts = new ArrayList<>();
            
            if (isArrival()) {
                nexts.add(pos);
                return nexts;
            }

            int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            for (int[] d : dir) {
                int ny = pos.y + d[0];
                int nx = pos.x + d[1];

                if (checkRange(ny, nx) && maze[ny][nx] != 5 && !visit[ny][nx]) {
                    nexts.add(new Position(ny, nx));
                }
            }

            return nexts;
        }
        
        public Cart copy() {
            if (isArrival()) {
                return this;
            }
            
            Cart copied = new Cart();
            copied.pos = pos;
            copied.dest = dest;
            copied.maze = maze;
            copied.n = n;
            copied.m = m;
            copied.visit = new boolean[n][m];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copied.visit[i][j] = visit[i][j];
                }
            }
            
            return copied;
        }
        
        private boolean checkRange(int y, int x) {
            return 0 <= y && y < n && 0 <= x && x < m;
        }
    }
    
    private static class Position {
        int y;
        int x;
        
        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        public boolean equals(Position other) {
            return y == other.y && x == other.x;
        }
    }
}