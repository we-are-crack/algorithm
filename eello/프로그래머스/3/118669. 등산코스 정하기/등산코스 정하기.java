import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]>[] connect = new List[n + 1];
        for (int i = 0; i < connect.length; i++) {
            connect[i] = new ArrayList<>();
        }
        
        for (int[] p : paths) {
            connect[p[0]].add(new int[] {p[1], p[2]});
            connect[p[1]].add(new int[] {p[0], p[2]});
        }
        
        boolean[] isSummit = new boolean[n + 1];
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
        });
        
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, 10_000_001);
        boolean[] isGate = new boolean[n + 1];
        
        for (int gate : gates) {
            pq.offer(new int[] {gate, 0});
            intensity[gate] = 0;
            isGate[gate] = true;
        }
        
        int[] answer = {50_001, 10_000_001};
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0], curIntensity = current[1];
            if (curIntensity > intensity[curNode] || curIntensity > answer[1]) {
                continue;
            }
            
            if (isSummit[curNode]) {
                if (curIntensity < answer[1] || curNode < answer[0]) {
                    answer = current;
                }
                
                continue;
            }
            
            for (int[] next : connect[curNode]) {
                int nextNode = next[0], nextIntensity = Math.max(curIntensity, next[1]);
                if (!isGate[nextNode] && nextIntensity < intensity[nextNode]) {
                    intensity[nextNode] = nextIntensity;
                    pq.offer(new int[] {nextNode, nextIntensity});
                }
            }
        }
        
        return answer;
    }
}