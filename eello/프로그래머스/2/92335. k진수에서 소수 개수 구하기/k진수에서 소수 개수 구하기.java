class Solution {
    
    public int solution(int n, int k) {
        String[] numbers = Integer.toString(n, k).split("0");
        
        int answer = 0;
        for (String number : numbers) {
            if (number.isBlank()) continue;
            
            if (isPrime(Long.parseLong(number))) {
                answer++;
            }
        }

        return answer;
    }
    
    private boolean isPrime(long num) {
        if (num == 1) return false;
        
        long sqrt = (long) Math.sqrt(num);
        for (long i = 2; i <= sqrt; i++) {
            if (num % i == 0) return false;
        }
        
        return true;
    }
}