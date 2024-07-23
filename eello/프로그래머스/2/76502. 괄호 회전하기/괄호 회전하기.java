import java.util.*;

class Solution {
    
    public int solution(String s) {
        for (int i = 0; i < s.length(); i++) {
            int validCount = validateBracket(s, i);
            if (validCount != 0) {
                return validCount;
            }
        }
        
        return 0;
    }
    
    /**
     *  start번 회전한 문자열이 올바른 괄호 문자열인지
     *  if 올바른 괄호 문자열 then 독립적으로 존재할 수 있는 올바른 괄호 문자열의 갯수(ex: {}[()]라면 {}, [()]로 2개)
     *  else 0
     */
    private int validateBracket(String s, int start) {
        int len = s.length();
        int validCount = 0;
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt((start + i) % len);
            
            if (isOpenBracket(ch)) {
                stack.push(ch);
            } else {
                if (!stack.isEmpty() && stack.peek() == getOpenBracketBy(ch)) {
                    stack.pop();
                    validCount += stack.isEmpty() ? 1 : 0;
                } else return 0;
            }
        }
        
        return stack.isEmpty() ? validCount : 0;
    }
    
    private boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }
    
    private char getOpenBracketBy(char close) {
        if (close == ')') return '(';
        else if (close == ']') return '[';
        else return '{';
    }
}