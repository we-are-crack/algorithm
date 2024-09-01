import java.util.*;

class Solution {
    
    public int solution(int n, int[] cores) {
        n -= cores.length;
        
        long time = 0; // 마지막 작업이 시작되는 시점
        long left = 0, right = 10_000 * 50_000;
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (noTask(n, cores, mid)) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        for (int i = 0; i < cores.length; i++) {
            n -= (time - 1) / cores[i];
        }
        
        int answer = 0;
        for (int i = 0; i < cores.length && n > 0; i++) {
            if (time % cores[i] == 0) {
                n -= 1;
                answer = i + 1;
            }
        }
        
        return answer;
    }
    
    private boolean noTask(int n, int[] cores, long time) {
        for (int i = 0; i < cores.length; i++) {
            n -= time / cores[i];
            if (n <= 0) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 시간초과 풀이
    public int solution(int n, int[] cores) {
        if (n <= cores.length) {
            return n;
        }
        
        Core[] core = new Core[cores.length];
        PriorityQueue<Core> processing = new PriorityQueue<>();
        
        int currentTime = 0;
        for (int i = 0; i < cores.length; i++) {
            core[i] = new Core(i + 1, cores[i]);
            core[i].start(currentTime);
            processing.offer(core[i]);
            n--;
        }
        
        int answer = 0;
        while (!processing.isEmpty()) {
            Core completeCore = processing.poll();
            currentTime = completeCore.getCompleteTime();
            
            if (n-- > 0) {
                answer = completeCore.getId();
                completeCore.start(currentTime);
                processing.offer(completeCore);
            }
        }
        
        return answer;
    }
    
    private static class Core implements Comparable<Core> {
        private int id;
        private int processingTime;
        private int completeTime;
        
        public Core(int id, int processingTime) {
            this.id = id;
            this.processingTime = processingTime;
        }
        
        public void start(int currentTime) {
            this.completeTime = currentTime + this.processingTime;
        }
        
        public int getId() {
            return this.id;
        }
        
        public int getCompleteTime() {
            return this.completeTime;
        }
        
        @Override
        public int compareTo(Core other) {
            return this.completeTime == other.completeTime ?
                this.id - other.id : this.completeTime - other.completeTime;
        }
    }
    */
}