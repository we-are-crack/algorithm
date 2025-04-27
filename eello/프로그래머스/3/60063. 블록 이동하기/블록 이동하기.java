import java.util.*;

class Solution {
    
    private static int n;
    private static int[][] board;
    private static BitSet[] visit;
    
    public int solution(int[][] board) {
        init(board);
        
        Robot robot = new Robot(0, 0, 0, 1, false);
        Queue<Robot> que = new ArrayDeque<>();
        
        que.add(robot);
        visit[0].set(1);
        visit[1].set(0);
        
        int answer = 0;
        l1: while (!que.isEmpty()) {
            int queSize = que.size();
            while (queSize-- > 0) {
                Robot curr = que.poll();
                
                if (curr.isArrive()) {
                    break l1;
                }
                
                que.addAll(curr.getNexts());
            }
            
            answer++;
        }
        
        return answer;
    }
    
    private void init(int[][] board) {
        this.board = board;
        n = board.length;
        visit = new BitSet[n * n];
        for (int i = 0; i < visit.length; i++) {
            visit[i] = new BitSet();
        }
    }
    
    private static class Robot {
        private Wheel front;
        private Wheel back;
        private boolean shape; // false: -, true: |
        
        public Robot(int fwr, int fwc, int bwr, int bwc, boolean shape) {
            front = new Wheel(fwr, fwc);
            back = new Wheel(bwr, bwc);
            this.shape = shape;
        }
        
        public List<Robot> getNexts() {
            List<Robot> nexts = new ArrayList<>();
            
            int[][] common = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            for (int[] d : common) {
                int fwr = front.r + d[0];
                int fwc = front.c + d[1];
                int fv = calculatePosValue(fwr, fwc);
                
                int bwr = back.r + d[0];
                int bwc = back.c + d[1];
                int bv = calculatePosValue(bwr, bwc);
                
                if (canGo(fwr, fwc, bwr, bwc) && !visit[fv].get(bv)) {
                    nexts.add(new Robot(fwr, fwc, bwr, bwc, shape));
                    visit[fv].set(bv);
                    visit[bv].set(fv);
                }
            }
            
            if (shape) {
                int i = -1;
                while (i <= 1) {
                    int nc = front.c + i;
                    
                    if (canGo(front.r, nc, back.r, nc)) {
                        int fv = calculatePosValue(front);
                        int bnv = calculatePosValue(front.r, nc);
                        if (!visit[fv].get(bnv)) {
                            nexts.add(new Robot(front.r, front.c, front.r, nc, !shape));
                            visit[fv].set(bnv);
                            visit[bnv].set(fv);
                        }
                        
                        int bv = calculatePosValue(back);
                        int fnv = calculatePosValue(back.r, nc);
                        if (!visit[bv].get(fnv)) {
                            nexts.add(new Robot(back.r, back.c, back.r, nc, !shape));
                            visit[bv].set(fnv);
                            visit[fnv].set(bv);
                        }
                    }
                    
                    i += 2;
                }
            } else {
                int i = -1;
                while (i <= 1) {
                    int nr = front.r + i;
                    
                    if (canGo(nr, front.c, nr, back.c)) {
                        int fv = calculatePosValue(front);
                        int bnv = calculatePosValue(nr, front.c);
                        if (!visit[fv].get(bnv)) {
                            nexts.add(new Robot(front.r, front.c, nr, front.c, !shape));
                            visit[fv].set(bnv);
                            visit[bnv].set(fv);
                        }
                        
                        int bv = calculatePosValue(back);
                        int fnv = calculatePosValue(nr, back.c);
                        if (!visit[bv].get(fnv)) {
                            nexts.add(new Robot(nr, back.c, back.r, back.c, !shape));
                            visit[bv].set(fnv);
                            visit[fnv].set(bv);
                        }
                    }
                    
                    i += 2;
                }
            }
            
            return nexts;
        }
        
        public boolean isArrive() {
            return (front.r == n - 1 && front.c == n - 1)
                || (back.r == n - 1 && back.c == n - 1);
        }
        
        private boolean canGo(int fwr, int fwc, int bwr, int bwc) {
            return 0 <= fwr && fwr < n && 0 <= fwc && fwc < n
                && 0 <= bwr && bwr < n && 0 <= bwc && bwc < n
                && board[fwr][fwc] == 0 && board[bwr][bwc] == 0;
        }
        
        private int calculatePosValue(Wheel w) {
            return calculatePosValue(w.r, w.c);
        }
        
        private int calculatePosValue(int r, int c) {
            return n * r + c;
        }
    }
    
    private static class Wheel {
        private int r;
        private int c;
        
        public Wheel(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}