import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] lab;
    static ArrayList<int[]> viruses;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int wallCount = 0;
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n][m];
        viruses = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if(lab[i][j] == 1) wallCount++;
                else if(lab[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }
        System.out.println(setWall(0, 0));
    }
    static int setWall(int cnt, int max) {
         if(cnt == 3) return  Math.max(max, n * m - (wallCount + 3 + spreadVirus()));
         for(int i = 0; i < lab.length; i++) {
             for(int j = 0; j < lab[i].length; j++) {
                 if(lab[i][j] == 0) {
                     lab[i][j] = 1;
                     max = setWall(cnt+1, max);
                     lab[i][j] = 0;
                 }
             }
         }
         return max;
    }
    static int spreadVirus() {
        int[][] graph = new int[n][m];
        for(int i = 0; i < n; i++)
            graph[i] = lab[i].clone();
        Queue<int[]> q = new LinkedList<>(viruses);
        int virusCount = 0;
        while(!q.isEmpty()) {
            int[] index = q.poll();
            virusCount++;
            for(int i = 0; i < 4; i++) {
                int row = index[0] + dy[i];
                int col = index[1] + dx[i];
                if(0 <= row && row < graph.length && 0 <= col && col < graph[0].length && graph[row][col] == 0) {
                    graph[row][col] = 2;
                    q.offer(new int[]{row, col});
                }
            }
        }
        return virusCount;
    }
}