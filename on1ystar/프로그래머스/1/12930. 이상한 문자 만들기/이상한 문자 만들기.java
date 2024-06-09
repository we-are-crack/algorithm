class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder(s.length());
        String[] words = s.split("");
        int wordIndex = 0;
        for(String word : words) {
            if(word.equals(" ")) {
                answer.append(word);
                wordIndex = 0;
            }
            else {
                if(wordIndex % 2 == 0) answer.append(word.toUpperCase());
                else answer.append(word.toLowerCase());
                wordIndex++;
            }
        }
        return answer.toString();
    }
}