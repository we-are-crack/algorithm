import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] rgb = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                rgb[i][j % 3] = Integer.parseInt(st.nextToken()) + Math.min(rgb[i - 1][(j + 1) % 3], rgb[i - 1][(j + 2) % 3]);
            }
        }

        System.out.println(Math.min(Math.min(rgb[n][0], rgb[n][1]), rgb[n][2]));
    }
}