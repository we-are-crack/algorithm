class Solution {
    
    private static final int MOD = 1_234_567;
    
    public int solution(int n) {
        int[] fibonacci = new int[n + 1];
        fibonacci[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            fibonacci[i] = (fibonacci[i - 1] + fibonacci[i - 2]) % MOD;
        }
        
        return fibonacci[n];
    }
}