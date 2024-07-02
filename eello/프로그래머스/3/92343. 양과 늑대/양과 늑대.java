import java.util.*;

class Solution {
    
    public int solution(int[] info, int[][] edges) {
        List<Integer>[] edge = new List[info.length];
        for (int i = 0; i < info.length; i++) {
            edge[i] = new ArrayList<>();
        }
        
        for (int[] node : edges) {
            edge[node[0]].add(node[1]);
        }
        
        List<Integer> nexts = new ArrayList<>();
        nexts.add(0);
        return dfs(info, edge, nexts, 0, 0, 0);
    }
    
    private int dfs(int[] info, List<Integer>[] edge, List<Integer> nexts, int node, int sheep, int wolf) {
        if (info[node] == 0) sheep++;
        else wolf++;
        
        if (sheep <= wolf) {
            return 0;
        }
        
        List<Integer> temp = new ArrayList<>(nexts);
        temp.addAll(edge[node]);
        temp.remove(temp.indexOf(node));
        
        int ret = sheep;
        for (int next : temp) {
            ret = Math.max(ret, dfs(info, edge, temp, next, sheep, wolf));
        }
        return ret;
    }
}