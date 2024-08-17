import java.util.*;

class Solution {
    
    public int solution(int x, int y, int n) {
        Queue<Integer> que = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        que.offer(x);
        
        int count = 0;
        while (!que.isEmpty()) {
            int qSize = que.size();
            
            while (qSize-- > 0) {
                int num = que.poll();
                
                if (num == y) {
                    return count;
                }
                
                visit(que, visited, num + n, y);
                visit(que, visited, num * 2, y);
                visit(que, visited, num * 3, y);
            }
            
            count++;
        }
        
        return -1;
    }
    
    private void visit(Queue<Integer> que, Set<Integer> visited, int num, int y) {
        if (num <= y && !visited.contains(num)) {
            visited.add(num);
            que.offer(num);
        }
    }
}