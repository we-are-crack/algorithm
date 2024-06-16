class Solution {
    
    public int solution(String s) {
        int answer = s.length();
        LOOP1 : while (answer > 1) {
            int range = s.length() - answer;
            for (int sIdx = 0; sIdx <= range; sIdx++) {
                if (isPalindrome(s, sIdx, answer)) {
                    break LOOP1;
                }
            }
            
            answer--;
        }
        return answer;
    }
    
    private boolean isPalindrome(String s, int sIdx, int len) {
        int left = sIdx, right = sIdx + len - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}