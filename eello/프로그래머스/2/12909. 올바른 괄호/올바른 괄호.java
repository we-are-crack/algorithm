class Solution {
    
    boolean solution(String s) {
        int open = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') open++;
            else {
                if (open == 0) return false;
                open--;
            }
        }
        
        return open == 0;
    }
}