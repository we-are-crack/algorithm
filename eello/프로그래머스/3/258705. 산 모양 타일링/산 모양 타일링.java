class Solution {
    
    public int solution(int n, int[] tops) {
        int mod = 10_007;
        
        int[] dp = new int[n * 2 + 1];
        dp[0] = 1;
        dp[1] = tops[0] == 0 ? 2 : 3;
        
        for (int i = 2; i < dp.length; i++) {
            if (i % 2 == 1 && tops[i / 2] == 1) {
                dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % mod;
            } else {
                dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
            }
        }
        
        return dp[dp.length - 1];
    }
}