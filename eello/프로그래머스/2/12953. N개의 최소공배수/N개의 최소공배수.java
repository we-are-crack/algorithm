import java.util.*;

class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int answer = arr[0];
        for (int i = 1; i < arr.length; i++) {
            answer = lcm(answer, arr[i]);
        }
        
        return answer;
    }
    
    private int lcm(int num1, int num2) {
        int gcd = gcd(num1, num2);
        return gcd == -1 ? num1 * num2 : num1 * (num2 / gcd);
    }
    
    private int gcd(int num1, int num2) {
        int big = num1 > num2 ? num1 : num2;
        int small = num1 > num2 ? num2 : num1;
        
        if (big % small == 0) return small;
        
        for (int i = small - 1; i > 1; i--) {
            if (big % i == 0 && small % i == 0) {
                return i;
            }
        }
        
        return -1;
    }
}