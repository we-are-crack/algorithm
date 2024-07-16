class Solution {
    
    public String solution(String s) {        
        boolean isHead = true;
        char[] ch = s.toLowerCase().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                isHead = true;
                continue;
            }
            
            if (isHead) {
                if (isAlphabet(ch[i])) {
                    ch[i] += 'A' - 'a';
                }
                
                isHead = false;
            }
        }
        
        return String.valueOf(ch);
    }
    
    private boolean isAlphabet(char ch) {
        return 'a' <= ch && ch <= 'z';
    }
}