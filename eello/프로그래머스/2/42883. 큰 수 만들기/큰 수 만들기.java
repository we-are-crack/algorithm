import java.util.*;

class Solution {
    
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < number.length(); i++) {            
            char ch = number.charAt(i);
            while (k > 0 && !stack.isEmpty() && stack.peek() < ch) {
                stack.pop();
                k--;
            }
            
            stack.push(ch);
        }
        
        while (k-- > 0) {
            stack.pop();
        }
        
        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }
        
        return sb.toString();
    }
}