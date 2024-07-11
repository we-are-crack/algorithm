import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] numbersArray = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            numbersArray[i] = String.valueOf(numbers[i]);
        Arrays.sort(numbersArray,(s1, s2) -> -s1.concat(s2).compareTo(s2.concat(s1)));
        String answer = String.join("", numbersArray);
        return answer.charAt(0) == '0' ? "0" : answer;
    }
}