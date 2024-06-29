import java.util.*;

class Solution {
    
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        Arrays.sort(scores, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            else return a[1] - b[1];
        });
        
        int answer = 0, max = scores[0][1];
        int wanhoScore = wanho[0] + wanho[1];
        for (int[] score : scores) {
            if (score[1] < max) {
                if (score == wanho) return -1;
                else continue;
            }
            
            max = score[1];
            
            if (wanhoScore < score[0] + score[1])
                answer++;
        }
        
        return answer + 1;
    }
}