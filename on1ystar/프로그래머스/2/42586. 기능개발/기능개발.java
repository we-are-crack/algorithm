import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int days = 0;
        int success = 0;
        int i = 0;
        while(i < progresses.length) {
            days += (int) Math.ceil((100 - (progresses[i] + days * speeds[i])) / (double) speeds[i]);
            for(int j = i; j < progresses.length; j++) {
                if(days * speeds[j] + progresses[j] >= 100) {
                    success++;
                    i++;
                }
                else {
                    answer.add(success);
                    success = 0;
                    break;
                }
            }
        }
        if(success != 0) answer.add(success);
        return answer.stream().mapToInt(e -> e).toArray();
    }
}