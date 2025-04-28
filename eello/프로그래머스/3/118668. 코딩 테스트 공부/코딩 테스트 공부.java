import java.util.*;

class Solution {
    
    public int solution(int alp, int cop, int[][] problems) {
        List<Problem> problem = new ArrayList<>();
        problem.add(new Problem(0, 0, 1, 0, 1));
        problem.add(new Problem(0, 0, 0, 1, 1));
        
        int goalAlp = 0, goalCop = 0;
        for (int[] p : problems) {
            goalAlp = Math.max(goalAlp, p[0]);
            goalCop = Math.max(goalCop, p[1]);
            problem.add(new Problem(p[0], p[1], p[2], p[3], p[4]));
        }
        
        Collections.sort(problem);
        
        Queue<int[]> que = new ArrayDeque<>();
        int[][] costs = new int[151][151];
        
        que.add(new int[]{alp, cop, 0});
        int max = 10_001;
        for (int i = 0; i < costs.length; i++) {
            Arrays.fill(costs[i], max);
        }
        costs[alp][cop] = 0;
        
        int answer = 10_001;
        while (!que.isEmpty()) {
            int[] curr = que.poll();
            if (curr[0] >= goalAlp && curr[1] >= goalCop) {
                answer = Math.min(answer, curr[2]);
                continue;
            }
            
            for (Problem p : problem) {
                if (curr[0] < p.alpReq && curr[1] < p.copReq) {
                    break;
                }
                
                if (p.alpReq <= curr[0] && p.copReq <= curr[1]) {
                    int nextAlp = Math.min(150, curr[0] + p.alpRwd);
                    int nextCop = Math.min(150, curr[1] + p.copRwd);
                    int nextCost = curr[2] + p.cost;
                    
                    if (nextCost < costs[nextAlp][nextCop]) {
                        costs[nextAlp][nextCop] = nextCost;
                        que.add(new int[]{nextAlp, nextCop, nextCost});
                    }
                }
            }
        }
        
        return answer;
    }
    
    private static class Problem implements Comparable<Problem> {
        private int alpReq;
        private int copReq;
        private int alpRwd;
        private int copRwd;
        private int cost;
        
        public Problem(int alpReq, int copReq, int alpRwd, int copRwd, int cost) {
            this.alpReq = alpReq;
            this.copReq = copReq;
            this.alpRwd = alpRwd;
            this.copRwd = copRwd;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Problem o) {
            if (alpReq != o.alpReq) {
                return alpReq - o.alpReq;
            }
            
            return copReq - o.copReq;
        }
    }
}