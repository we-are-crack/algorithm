import java.util.*;

class Solution {
    
    public int[] solution(String msg) {
        Map<String, Integer> index = new HashMap<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            index.put(String.valueOf(i), i - 'A' + 1);
        }
        
        List<Integer> answerList = new ArrayList<>();
        
        int left = 0, nextIdx = 27;
        while (left < msg.length()) {
            int right = left + 2;
            while (right <= msg.length() && index.containsKey(msg.substring(left, right))) {
                right++;
            }
            
            answerList.add(index.get(msg.substring(left, right - 1)));
            index.put(msg.substring(left, Math.min(right, msg.length())), nextIdx++);
            
            left = right - 1;
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}