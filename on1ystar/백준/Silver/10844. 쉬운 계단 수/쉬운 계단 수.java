import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n == 1) {
            System.out.println(9);
            return;
        }
        long[][] d = new long[n][10];
        Arrays.fill(d[0], 1);
        for(int i = 1; i < n; i++) {
            if(i != n - 1) d[i][0] = d[i - 1][1];
            for(int j = 1; j < 9; j++) {
                d[i][j] = (d[i - 1][j - 1] + d[i - 1][j + 1]) % 1_000_000_000l;
            }
            d[i][9] = d[i - 1][8];
        }
        System.out.println(Arrays.stream(d[n - 1]).sum() % 1_000_000_000l);;
    }
}