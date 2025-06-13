import java.util.*;

class Solution {
    
    public int[] solution(int target) {
        int size = 100_001;
        int[] dp = new int[size]; // dp[i] = i번 점수까지 던져야하는 다트 횟수의 최솟값
        int[] sob = new int[size]; // sob[i] = i번 점수까지 던졌을 때 최대 싱글 또는 불 횟수
        
        Arrays.fill(dp, size);
        dp[50] = 1;
        sob[50] = 1;
        
        List<Integer> available = new ArrayList<>();
        available.add(50);
        
        for (int i = 1; i <= 20; i++) {
            sob[i] = 1;
            for (int j = 1; j <= 3; j++) {
                dp[i * j] = 1;
                available.add(i * j);
            }
        }
        
        available.sort((a, b) -> Integer.compare(a, b));
        
        for (int i = 21; i <= target; i++) {
            for (int j = 1; j < available.size(); j++) {
                int score = available.get(j);
                if (i <= score) {
                    break;
                }
                
                int t = dp[i - score] + dp[score];
                int sb = sob[i - score] + sob[score];
                
                if (t < dp[i]) {
                    dp[i] = t;
                    sob[i] = sb;
                } else if (t == dp[i] && sob[i] < sb) {
                    sob[i] = sb;
                }
            }
        }
        
        return new int[] {dp[target], sob[target]};
    }
}