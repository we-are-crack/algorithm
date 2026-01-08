import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            long[][] d = new long[n+1][2];
            if(n == 0) {
                System.out.println("1 0");
                continue;
            }
            d[0] = new long[]{1, 0};
            d[1] = new long[]{0, 1};
            for(int j = 2; j <= n; j++) {
                d[j][0] = d[j-1][0] + d[j-2][0];
                d[j][1] = d[j-1][1] + d[j-2][1];
            }
            System.out.println(d[n][0] + " " + d[n][1]);
        }
    }
}