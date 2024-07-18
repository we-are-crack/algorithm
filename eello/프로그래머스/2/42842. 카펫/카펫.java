class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int temp = (brown + 4) / 2;
        for (int h = temp - 1; h >= temp / 2; h--) {
            if (isValid(h, temp - h, yellow)) {
                answer[0] = h;
                answer[1] = temp - h;
                break;
            }
        }
        
        return answer;
    }
    
    private boolean isValid(int h, int w, int yellow) {
        return (h - 2) * (w - 2) == yellow;
    }
}