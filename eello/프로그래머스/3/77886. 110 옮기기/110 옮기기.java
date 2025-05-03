class Solution {
    
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            answer[i] = transform(s[i]);
        }
        
        return answer;
    }
    
    private String transform(String str) {
        StringBuilder sb = new StringBuilder(str);
        
        int i = 0, count = 0;
        while (i < str.length() - 2) {
            if (isTarget(sb, i, "110")) {
                sb.replace(i, i + 3, "");
                count++;
                i = Math.max(0, i - 2);
            } else {
                i++;
            }
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