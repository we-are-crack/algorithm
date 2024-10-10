import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        int[][] arr = new int[n + 2][n+ 2];

        for (int i = 2; i <= n + 1; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 2; j <= n + 1; j++) {
                arr[i][j] = (arr[i - 1][j] - arr[i - 1][j - 1]) + arr[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + 1;
            int y1 = Integer.parseInt(st.nextToken()) + 1;
            int x2 = Integer.parseInt(st.nextToken()) + 1;
            int y2 = Integer.parseInt(st.nextToken()) + 1;

            sb.append(arr[x2][y2] - arr[x1 - 1][y2] - arr[x2][y1 - 1] + arr[x1 - 1][y1 - 1]);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}