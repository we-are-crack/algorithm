import java.util.*;

class Solution {
    public int solution(int[][] targets) {       
        Arrays.sort(
            targets,
            (a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0];
                else return a[1] - b[1];
            }
        );
        
        int answer = 1;
        int right = targets[0][1];
        for (int i = 1; i < targets.length; i++) {
            if (right <= targets[i][0]) {
                answer++;
                right = targets[i][1];
            } else {
                right = Math.min(right, targets[i][1]);
            }
        }
        
        return answer;
    }
}