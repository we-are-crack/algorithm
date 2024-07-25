import java.util.*;

class Solution {
    
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        int min = 0, max = 10000;
        int answer = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            
            if (isValid(citations, mid)) {
                min = mid + 1;
                answer = mid;
            } else max = mid - 1;
        }
        
        return answer;
    }
    
    private boolean isValid(int[] citations, int h) {
        int left = 0, right = citations.length - 1;
        int rightIdx = 0;
        
        while (left <= right) {
            int m = (left + right) / 2;
            if (citations[m] <= h) {
                rightIdx = m + 1;
                left = m + 1;
            } else right = m - 1;
        }
        
        int leftIdx = rightIdx;
        while (--leftIdx >= 0 && citations[leftIdx] == h);
        leftIdx++;
                
        return h <= citations.length - leftIdx // h번 이상 인용된 논문이 인용한 횟수가 h편 이상
            && rightIdx <= h; // 나머지 논문이 h번 이하로 인용    
    }
}