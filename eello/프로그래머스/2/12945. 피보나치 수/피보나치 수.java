class Solution {
    
    private static final int MOD = 1_234_567;
    
    public int solution(int n) {
        int pp = 0, p = 1;
        for (int i = 2; i <= n; i++) {
            p += pp;
            pp = p - pp;
            p %= MOD;
        }
        
        return p;
    }
}