class Solution {
        
    public int solution(int n) {
        if (n < 3) return n;
        
        int mod = 1_000_000_007;
        int p = 2, pp = 1, count = 3;
        while (count++ <= n) {
            p = p + pp;
            pp = p - pp;
            p %= mod;
        }
        
        return p;
    }
}