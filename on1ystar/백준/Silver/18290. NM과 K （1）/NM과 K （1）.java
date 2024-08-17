import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static int[][] matrix;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n + 2][m + 2];
        visited = new boolean[n + 2][m + 2];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, Integer.MIN_VALUE, 0));
    }
    private static int dfs(int sum, int max, int cnt) {
        if(cnt == k) return Math.max(sum, max);
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(!visited[i][j] && isNearbyOk(i, j)) {
                    visited[i][j] = true;
                    max = Math.max(max, dfs(sum + matrix[i][j], max, cnt + 1));
                    visited[i][j] = false;
                }
            }
        }
        return max;
    }
    private static boolean isNearbyOk(int i, int j) {
        if(visited[i - 1][j] || visited[i][j - 1] || visited[i + 1][j] || visited[i][j + 1]) return false;
        return true;
    }
 }