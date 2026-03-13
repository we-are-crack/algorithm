class Solution {
    
    private int n;
    
    public int[] solution(int n) {
        this.n = n;
        
        int size = n * (n + 1) / 2;
        int[] answer = new int[size];
        answer[0] = 1;
        
        int number = 1;
        int r = 0, c = 0;
        int[][] dir = {{1, 0}, {0, 1}, {-1, -1}};
        while (number < size) {
            for (int[] d : dir) {
                while (true) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    
                    if (!isInBounds(nr, nc)) {
                        break;
                    }
                    
                    int index = flattenIndex(nr, nc);
                    if (answer[index] == 0) {
                        answer[index] = ++number;
                        r = nr;
                        c = nc;
                    } else break;
                }
            }
        }
        
        return answer;
    }
    
    private boolean isInBounds(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c <= r;
    }
    
    private int flattenIndex(int r, int c) {
        return r * (r + 1) / 2 + c;
    }
}