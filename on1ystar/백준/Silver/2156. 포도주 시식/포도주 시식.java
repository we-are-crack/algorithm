import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] d = new int[n + 1][2];
        int wine = Integer.parseInt(br.readLine());
        if(n == 1) {
            System.out.println(wine);
            return;
        }
        d[1] = new int[]{wine, wine};
        wine = Integer.parseInt(br.readLine());
        d[2] = new int[]{wine + d[1][1], wine};
        for(int i = 3; i <= n; i++) {
            wine = Integer.parseInt(br.readLine());
            d[i][0] = wine + d[i - 1][1];
            d[i][1] = wine + Math.max(Math.max(d[i - 2][0], d[i - 2][1]), Math.max(d[i - 3][0], d[i - 3][1]));
        }
        System.out.println(Math.max(Math.max(d[n][0], d[n][1]), Math.max(d[n - 1][0], d[n - 1][1])));
    }
}