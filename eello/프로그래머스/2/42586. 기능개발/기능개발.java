import java.util.*;

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        
        List<Integer> temp = new ArrayList<>();
        
        int prev = (100 - progresses[0]) / speeds[0];
        prev += (100 - progresses[0]) % speeds[0] == 0 ? 0 : 1;
        
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            int remain = 100 - progresses[i];
            int speed = speeds[i];
            
            int comp = remain / speed;
            comp += remain % speed == 0 ? 0 : 1;
            
            if (prev >= comp) {
                cnt++;
            } else {
                temp.add(cnt);
                prev = comp;
                cnt = 1;
            }
        }
        temp.add(cnt);
        
        int[] answer = new int[temp.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = temp.get(i);
        }
        
        return answer;
    }
}