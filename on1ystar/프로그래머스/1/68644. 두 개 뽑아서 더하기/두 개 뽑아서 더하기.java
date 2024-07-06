import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        TreeSet<Integer> hs = new TreeSet<>();
        for(int i = 0; i < numbers.length - 1; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                hs.add(numbers[i] + numbers[j]);
            }
        }
        Integer[] answer = hs.toArray(new Integer[0]);
        return Arrays.stream(answer).mapToInt(i -> i).toArray();
    }
}