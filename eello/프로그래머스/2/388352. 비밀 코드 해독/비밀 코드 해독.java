import java.util.*;

class Solution {
    
    private int n;
    private int[] query;
    private int[] answer;
    
    public int solution(int n, int[][] q, int[] ans) {
        init(n, q, ans);
        return dfs(1, 0, 0);
    }
    
    private void init(int n, int[][] q, int[] ans) {
        this.n = n;
        
        query = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            query[i] = toBitmask(q[i]);
        }
        
        
        answer = ans;
    }
    
    private int toBitmask(int[] arr) {
        int bitmask = 0;
        for (int number : arr) {
            bitmask |= (1 << number);
        }
        return bitmask;
    }
    
    /**
        cNum: 현재 선택할지 안할지 결정할 숫자 (오름차순 진행, cNum <= n까지 진행)
        cand: 현재까지 선택한 숫자 조합 (비트마스킹 적용)
        sel: 선택된 숫자 개수 (sel <= 5 진행)
    */
    private int dfs(int cNum, int cand, int sel) {
        if (sel == 5) {
            if (isValid(cand)) return 1;
            else return 0;
        }
        
        if (n < cNum) return 0;
        
        int ret = 0;
        ret += dfs(cNum + 1, cand | (1 << cNum), sel + 1); // 현재 숫자를 선택한 경우
        ret += dfs(cNum + 1, cand, sel); // 현재 숫자를 선택하지 않은 경우
        
        return ret;
    }
    
    private boolean isValid(int candidate) {
        for (int i = 0; i < query.length; i++) {
            if (Integer.bitCount(query[i] & candidate) != answer[i]) {
                return false;
            }
        }
        return true;
    }
}