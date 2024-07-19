import java.util.*;

class Solution {
    
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int answer = 0, l = 0, r = people.length - 1;
        while (l <= r) {
            answer++;
            if (people[l] + people[r] <= limit) {
                l++;
            }
            r--;
        }
        
        return answer;
    }
}