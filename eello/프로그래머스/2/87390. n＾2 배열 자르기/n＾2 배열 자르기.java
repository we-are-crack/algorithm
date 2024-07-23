class Solution {
    
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        for (long i = left; i <= right; i++) {
            int r = (int) (i / n);
            int c = (int) (i % n);
            
            answer[(int) (i - left)] = Math.max(r, c) + 1;
        }
        
        return answer;
    }
}