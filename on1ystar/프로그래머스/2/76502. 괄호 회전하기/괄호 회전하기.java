import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length(); i++) {
            if(isValid(sb.toString())) answer++;
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
        }
        return answer;
    }
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') stack.push(ch);
            else {
                if(stack.isEmpty()) return false;
                else if(ch == ')' && stack.peek() == '(') stack.pop();
                else if(ch == '}' && stack.peek() == '{') stack.pop();
                else if(ch == ']' && stack.peek() == '[') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
}