import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] stairs = new int[n + 1][2];
        stairs[1][0] = Integer.parseInt(br.readLine());
        stairs[1][1] = stairs[1][0];
        for (int i = 2; i <= n; i++) {
            int value = Integer.parseInt(br.readLine());
            stairs[i][0] = stairs[i - 1][1] + value;
            stairs[i][1] = Math.max(stairs[i - 2][0], stairs[i - 2][1]) + value;
        }

        System.out.println(Math.max(stairs[n][0], stairs[n][1]));
    }
}