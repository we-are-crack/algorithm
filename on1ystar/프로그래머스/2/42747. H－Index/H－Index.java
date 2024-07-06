import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for(int i = citations.length; i > 0; i--) {
            if(i <= citations[citations.length - i]) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}