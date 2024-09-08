class Solution {
    
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int temp = 0, cnt = 1;
        while (temp < n) {
            int a = (int) Math.pow(3, cnt);
            double b = Math.pow(3, cnt - 1);
            
            int rem = (n - temp) % a;
            if (rem == 0) {
                answer.append(4);
            } else {
                int num = (int) Math.ceil(rem / b);
                answer.append(num == 3 ? 4 : num);
            }
            
            temp += a;
            cnt++;
        }
        
        return answer.reverse().toString();
    }
}