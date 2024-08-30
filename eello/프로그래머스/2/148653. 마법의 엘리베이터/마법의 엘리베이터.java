class Solution {
    
    public int solution(int storey) {
        return move(storey, 0);
    }
    
    private int move(int storey, int count) {
        if (storey <= 1) {
            return count + storey;
        }
        
        int tail = storey % 10;
        int next = storey / 10;
        
        int ret = move(next, count + tail); // 내려가는 경우
        ret = Math.min(ret, move(next + 1, count + (10 - tail))); // 올라가는 경우
        
        return ret;
    }
}