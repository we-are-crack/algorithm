import java.util.*;

class Solution {
    
    private int[][] nodes, orders;
    private boolean[] visited;
    private int preIdx, postIdx;
    
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        
        nodes = new int[n][3];
        for (int i = 0; i < n; i++) {
            nodes[i] = new int[] {nodeinfo[i][0], nodeinfo[i][1], i + 1};
        }
        Arrays.sort(nodes, (a, b) -> a[0] - b[0]);
        
        visited = new boolean[n];
        orders = new int[2][n];
        
        int rootIdx = 0;
        int maxY = 0;
        for (int i = 0; i < n; i++) {
            if (maxY < nodes[i][1]) {
                maxY = nodes[i][1];
                rootIdx = i;
            }
        }
        dfs(rootIdx);
        
        return orders;
    }
    
    private void dfs(int idx) {
        orders[0][preIdx++] = nodes[idx][2];
        visited[idx] = true;
        
        Integer left = getLeftNodeIndex(idx);
        Integer right = getRightNodeIndex(idx);
        
        if (left != null) dfs(left);
        if (right != null) dfs(right);
        
        orders[1][postIdx++] = nodes[idx][2];
    }
    
    private Integer getLeftNodeIndex(int idx) {
        int maxY = -1;
        Integer left = null;
        while (--idx >= 0 && !visited[idx]) {
            if (maxY < nodes[idx][1]) {
                maxY = nodes[idx][1];
                left = idx;
            }
        }
        
        return left;
    }
    
    private Integer getRightNodeIndex(int idx) {
        int maxY = -1;
        Integer right = null;
        while (++idx < nodes.length && !visited[idx]) {
            if (maxY < nodes[idx][1]) {
                maxY = nodes[idx][1];
                right = idx;
            }
        }
        
        return right;
    }
}