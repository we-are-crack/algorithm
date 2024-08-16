import java.util.*;

class Solution {
    
    public int solution(int N, int number) {
        if (N == number) return 1;
        
        Set<Integer>[] dp = new Set[9];
        
        int n = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = new HashSet<>();
            
            n += N * (int) Math.pow(10, i - 1);
            dp[i].add(n);
        }
        
        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j < i; j++) {
                dp[i].addAll(calculate(dp[i - j], dp[j]));
                if (dp[i].contains(number)) {
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    /*
     *  s1과 s2에 포함된 모든 원소들 간의 사칙연산 결과를 담은 set을 리턴
     */
    private Set<Integer> calculate(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> result = new HashSet<>();
        for (Integer n1 : s1) {
            for (Integer n2 : s2) {
                result.add(n1 + n2);
                result.add(n1 * n2);
                
                result.add(n1 - n2);
                result.add(n2 - n1);
                
                if (n2 != 0) result.add(n1 / n2);
                if (n1 != 0) result.add(n2 / n1);
            }
        }
        
        return result;
    }
}