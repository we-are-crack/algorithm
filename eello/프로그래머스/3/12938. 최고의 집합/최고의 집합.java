class Solution {
    public int[] solution(int n, int s) {
        if (n > s) return new int[] {-1};
        
        int[] answer = new int[n];
        int idx = 0;
        while (s > 0) {
            int mok = s / n--;
            answer[idx++] = mok;
            s -= mok;
        }
        
        return answer;
    }
}