import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for(int i = citations.length; i > 0; i--) {
            int cnt = i;
            for (int j = citations.length - 1; j >= 0; j--) {
                if (i <= citations[j]) {
                    if (--cnt == 0) break;
                }
                else break;
            }
            if(cnt == 0) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}