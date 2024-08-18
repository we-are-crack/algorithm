class Solution {
        
    public int solution(int n) {
        int p = 2, pp = 1;
        
        if (n == 1) return pp;
        if (n == 2) return p;
        
        int count = 3;
        int mod = 1_000_000_007;
        while (count++ <= n) {
            p = p + pp;
            pp = p - pp;
            p %= mod;
        }
        
        return p;
    }
}