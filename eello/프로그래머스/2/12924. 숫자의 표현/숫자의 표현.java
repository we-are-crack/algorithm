class Solution {
    
    public int solution(int n) {
        int answer = 0, left = 0, right = 1;
        int sum = left + right;
        
        while (left <= right) {
            if (sum == n) {
                answer++;
            }
            
            if (sum <= n) {
                sum += ++right;
            } else {
                sum -= left++;
            }
        }
        
        return answer;
    }
}