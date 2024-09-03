class Solution {

    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n + 1][n + 1];

        for (int[] result : results) {
            graph[result[0]][result[1]] = 1;
        }

        for (int i = 1; i <= n; i++) {
            int winCount = trackWin(graph, i, 0, new boolean[n + 1]);
            int loseCount = trackLose(graph, i, 0, new boolean[n + 1]);
            if (winCount + loseCount == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    private int trackWin(int[][] graph, int winner, int cnt, boolean[] visited) {
        if (cnt == graph.length - 2) {
            return cnt;
        }

        for (int i = 1; i < graph.length; i++) {
            if (graph[winner][i] == 1 && !visited[i]) {
                visited[i] = true;
                cnt = trackWin(graph, i, cnt + 1, visited);
            }
        }

        return cnt;
    }
    
    private int trackLose(int[][] graph, int loser, int cnt, boolean[] visited) {
        if (cnt == graph.length - 2) {
            return cnt;
        }
        
        for (int i = 1; i < graph.length; i++) {
            if (graph[i][loser] == 1 && !visited[i]) {
                visited[i] = true;
                cnt = trackLose(graph, i, cnt + 1, visited);
            }
        }
        
        return cnt;
    }
}