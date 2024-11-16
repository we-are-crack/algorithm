import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] isNonPrime = new boolean[n + 1];
        isNonPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            for (int j = i; j <= n; j++) {
                if (i * j > n) {
                    break;
                }

                isNonPrime[i * j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n; i++) {
            if (!isNonPrime[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }
}