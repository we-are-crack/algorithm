import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] d = new int[n + 1][2];
        int stair = Integer.parseInt(br.readLine());
        d[1] = new int[]{stair, stair};
        for(int i = 2; i <= n; i++) {
            stair = Integer.parseInt(br.readLine());
            d[i][0] = stair + d[i - 1][1];
            d[i][1] = stair + Math.max(d[i - 2][0], d[i - 2][1]);
        }
        System.out.println(Math.max(d[n][0], d[n][1]));
    }
}