import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int[] pastDistances = new int[truck_weights.length];
        bridge.add(0);
        int bridgeWeight = truck_weights[0];
        int time = 0;
        while(!bridge.isEmpty()) {
            int indexOfWaitingTruck = bridge.peek() + bridge.size();
            for(int i = bridge.peek(); i < indexOfWaitingTruck; i++)
                pastDistances[i]++;
            if(pastDistances[bridge.peek()] > bridge_length) 
                bridgeWeight -= truck_weights[bridge.poll()];
            if(indexOfWaitingTruck < truck_weights.length) {
                if(bridgeWeight + truck_weights[indexOfWaitingTruck] <= weight) {
                    bridge.add(indexOfWaitingTruck);
                    bridgeWeight += truck_weights[indexOfWaitingTruck];
                    pastDistances[indexOfWaitingTruck]++;
                }
            }
            time++;
        }
        return time;
    }
}