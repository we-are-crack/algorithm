import java.util.*;

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> crossing = new ArrayDeque<>(); // [truckW, arrivalTime]
        
        int answer = 0, crossingW = 0, time = 1;
        for (int i = 0; i < truck_weights.length; i++) {
            int truckW = truck_weights[i];
            while (!crossing.isEmpty() && !(crossing.size() < bridge_length && crossingW + truckW <= weight)) {
                // i번째 트럭이 다리 위에 올라갈 수 있을 때까지 다리 위에 있는 트럭을 도착시킨다.
                int[] crossed = crossing.poll();
                crossingW -= crossed[0];
                time = Math.max(time, crossed[1]);
            }
            
            int arrival = time + bridge_length;
            answer = arrival;
            crossing.offer(new int[] {truckW, arrival});
            crossingW += truckW;
            
            time++;
        }
        
        return answer;
    }
}