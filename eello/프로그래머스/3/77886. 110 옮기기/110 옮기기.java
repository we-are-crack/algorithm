import java.util.*;

class Solution {
    
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            answer[i] = transform(s[i]);
        }
        
        return answer;
    }
    
    private String transform(String str) {
        int count = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : str.toCharArray()) {
            if (stack.size() >= 2 && ch == '0') {
                char p = stack.pop();
                char pp = stack.pop();
                
                if (p == '1' && pp == '1') {
                    count++;
                    continue;
                }
                
                stack.push(pp);
                stack.push(p);
            }
            
            stack.push(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        
        String insert = "110".repeat(count);
        int insertAt = sb.length();
        
        while (insertAt > 0 && sb.charAt(insertAt - 1) == '1') {
            insertAt--;
        }
        
        sb.insert(insertAt, insert);
        return sb.toString();
    }
    
    private boolean isTarget(StringBuilder sb, int idx, String target) {
        if (idx < 0 || sb.length() < idx + target.length()) {
            return false;
        }
        
        for (int i = 0 ; i < target.length(); i++) {
            if (sb.charAt(idx + i) != target.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}