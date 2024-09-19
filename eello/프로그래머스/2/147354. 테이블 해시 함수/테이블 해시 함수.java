import java.util.*;

class Solution {
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a, b) -> {
            return a[col - 1] != b[col - 1] ?
                a[col - 1] - b[col - 1] : b[0] - a[0];
        });
        
        int[] s = new int[row_end - row_begin + 1];
        int cnt = 0;
        for (int i = row_begin - 1; i < row_end; i++) {
            int sum = 0;
            for (int c : data[i]) {
                sum += c % (i + 1);
            }
            
            s[cnt++] = sum;
        }
        
        int answer = s[0];
        for (int i = 1; i < s.length; i++) {
            answer ^= s[i];
        }
        
        return answer;
    }
}