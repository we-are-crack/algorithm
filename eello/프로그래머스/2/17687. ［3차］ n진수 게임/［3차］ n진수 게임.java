class Solution {
    
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        int number = 0, maxTurn = p + (m * t) - 1;
        while (sb.length() < maxTurn) {
            sb.append(Integer.toString(number, n));
            number++;
        }
        
        StringBuilder answer = new StringBuilder();
        for (int turn = p - 1; turn < maxTurn; turn += m) {
            answer.append(sb.charAt(turn));
        }
        
        return answer.toString().toUpperCase();
    }
}