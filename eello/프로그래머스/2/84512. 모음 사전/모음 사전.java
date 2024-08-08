class Solution {
    
    private static final int MAX_WORD_LENGTH = 5;
    private int answer, idx;
    
    public int solution(String word) {
        dict(word, "");
        return answer;
    }
    
    private void dict(String target, String word) {
        if (target.equals(word)) {
            answer = idx;
        }
        
        if (answer != 0 || word.length() == MAX_WORD_LENGTH) {
            return;
        }
        
        char[] vowel = {'A', 'E', 'I', 'O', 'U'};
        for (char v : vowel) {
            idx++;
            dict(target, word + v);
        }
    }
}