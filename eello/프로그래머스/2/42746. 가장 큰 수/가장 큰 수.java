import java.util.*;

class Solution {
    
    public String solution(int[] numbers) {
        int zeroCount = 0;
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                zeroCount++;
            }
            
            strNumbers[i] = Integer.toString(numbers[i]);
        }
        
        if (zeroCount == numbers.length) {
            return "0";
        }
        
        Arrays.sort(strNumbers, (a, b) -> {
            if (a.length() == b.length()) {
                return b.compareTo(a);
            }
            
            String ab = a.concat(b);
            String ba = b.concat(a);
            return ba.compareTo(ab);
        });
        
        return String.join("", strNumbers);
    }
}