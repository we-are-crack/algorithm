class Solution {
    
    public long[] solution(long[] numbers) {       
        long[] answer = new long[numbers.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = f(numbers[i]);
        }
        
        return answer;
    }
    
    private long f(long x) {
        long k = 1;
        while (Long.bitCount(x ^ (x + k)) > 2) {
            k *= 2;
        }
               
        return x + k;
    }
}