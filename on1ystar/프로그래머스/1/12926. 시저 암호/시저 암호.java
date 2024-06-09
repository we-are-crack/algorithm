class Solution {
    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();
        char encryptedAlphabet;
        for(char alphabet : s.toCharArray()) {
            if(alphabet == ' ') {
                answer.append(' ');
                continue;
            }
            encryptedAlphabet = (char) (alphabet + n);
            if('A' <= alphabet && alphabet <= 'Z' && encryptedAlphabet > 'Z') {
                encryptedAlphabet -= 26;
            }
            else if('a' <= alphabet && alphabet <= 'z' && encryptedAlphabet > 'z') {
                encryptedAlphabet -= 26;
            }
            answer.append(encryptedAlphabet);
        }

        return answer.toString();
    }
}