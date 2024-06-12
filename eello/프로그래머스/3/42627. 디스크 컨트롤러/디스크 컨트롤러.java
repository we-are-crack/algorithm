import java.util.*;

class Solution {
    
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> created = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else return a[1] - b[1];
        });
        PriorityQueue<int[]> ready = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int[] job : jobs) {
            created.add(job);
        }
        
        int time = 0;
        int totalResTime = 0;
        while (!created.isEmpty() || !ready.isEmpty()) {
            if (ready.isEmpty()) {
                time = created.peek()[0];
                ready.add(created.poll());
            }
            
            int[] running = ready.poll();
            time += running[1];
            totalResTime += (time - running[0]);
            while (!created.isEmpty() && created.peek()[0] <= time) {
                ready.add(created.poll());
            }
        }
        
        return totalResTime / jobs.length;
    }
}