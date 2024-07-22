class Solution {
    
    private static final int MOD = 1_234_567;
    
    public long solution(int n) {
        if (n < 3) return n;
        
        int pp = 1, p = 2;
        for (int i = 3; i <= n; i++) {
            p += pp;
            pp = p - pp;
            p %= MOD;
        }
        
        return p;
    }
}