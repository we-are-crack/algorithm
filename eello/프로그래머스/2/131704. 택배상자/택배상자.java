import java.util.*;

class Solution {
    
    public int solution(int[] order) {
        int n = order.length;
        
        Stack<Integer> second = new Stack<>();
        int answer = 0, next = 0;
        for (int box = 1; box <= n; box++) {
            second.push(box);
            while (!second.isEmpty() && second.peek() == order[next]) {
                answer++;
                next++;
                second.pop();
            }
        }
        
        return answer;
    }
}