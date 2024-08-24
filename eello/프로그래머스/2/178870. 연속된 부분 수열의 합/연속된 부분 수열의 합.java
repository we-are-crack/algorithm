class Solution {
    
    public int[] solution(int[] sequence, int k) {
        int len = sequence.length;
        int[] answer = {0, len};
        
        int left = 0, right = 0, sum = sequence[0];
        while (right < len) {
            if (sum > k) {
                sum -= sequence[left++];
            } else {
                if (sum == k && answer[1] - answer[0] > right - left) {
                    answer[0] = left;
                    answer[1] = right;
                }
                
                right++;
                if (right < len) {
                    sum += sequence[right];
                }
            }
        }
        
        return answer;
    }
}