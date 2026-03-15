import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<Plan> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.start));
        for (String[] plan : plans) {
            pq.add(new Plan(plan));
        }
        
        Stack<Plan> unfinished = new Stack<>();
        
        String[] answer = new String[plans.length];
        int ai = 0;
        while (!pq.isEmpty()) {
            Plan p = pq.poll();
            
            int playTime = 100;
            if (!pq.isEmpty()) {
                playTime = Math.min(p.playTime, pq.peek().start - p.start);
            }
            
            if (!p.play(playTime)) {
                unfinished.push(p);
                continue;
            }
            
            answer[ai++] = p.subject;
            if (pq.isEmpty()) {
                break;
            }
            
            int endTime = p.start + playTime;
            int freeTime = pq.peek().start - endTime;
            while (freeTime > 0 && !unfinished.isEmpty()) {
                Plan unf = unfinished.peek();
                int pt = Math.min(unf.playTime, freeTime);

                if (unf.play(pt)) {
                    unfinished.pop();

                    answer[ai++] = unf.subject;
                }

                freeTime -= pt;
            }
        }
        
        while (!unfinished.isEmpty()) {
            answer[ai++] = unfinished.pop().subject;
        }
        
        return answer;
    }
    
    private static class Plan {
        String subject;
        int start;
        int playTime;
        
        public Plan(String[] plan) {
            this.subject = plan[0];
            this.start = toMinute(plan[1]);
            this.playTime = Integer.parseInt(plan[2]);
        }
        
        private static int toMinute(String time) {
            String[] t = time.split(":");
            return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }
        
        // 과제를 끝내면 true, 끝내지 못하면 false 리턴
        public boolean play(int time) {
            this.playTime -= time;
            return playTime <= 0;
        }
        
        @Override
        public String toString() {
            return "Plan(subject= " + subject +")";
        }
    }
}