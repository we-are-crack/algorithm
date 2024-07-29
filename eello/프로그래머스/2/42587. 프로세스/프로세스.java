import java.util.*;

class Solution {
    
    public int solution(int[] priorities, int location) {
        Queue<int[]> jobQueue = new ArrayDeque<>();
        for (int loc = 0; loc < priorities.length; loc++) {
            jobQueue.offer(new int[] {priorities[loc], loc});
        }
        
        Arrays.sort(priorities);
        
        int wait = priorities.length - 1;
        while (true) {
            int highestPriority = priorities[wait];
            
            int[] job = jobQueue.poll();
            if (job[0] != highestPriority) {
                jobQueue.offer(job);
            } else {
                if (job[1] == location) {
                    return priorities.length - wait;
                }
                
                wait--;
            }
        }
    }
}