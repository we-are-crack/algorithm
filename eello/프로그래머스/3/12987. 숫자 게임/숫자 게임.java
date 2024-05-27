import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = 0;
        int score = 0;
        for (int bIdx = 0; bIdx < B.length; bIdx++) {
            if (A[aIdx] < B[bIdx]) {
                aIdx++;
                score++;
            }
        }
        
        return score;
    }
}