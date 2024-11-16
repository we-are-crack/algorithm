import java.io.*;

public class Main {

    private static final int MAX_VALUE = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean[] nonPrime = new boolean[MAX_VALUE];
        nonPrime[1] = true;

        for (int i = 3; i < Math.sqrt(MAX_VALUE); i+= 2) {
            for (int j = i; j < MAX_VALUE; j += 2) {
                if (i * j >= MAX_VALUE) {
                    break;
                }

                nonPrime[i * j] = true;
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            for (int i = 3; i < n; i += 2) {
                if (!nonPrime[i] && !nonPrime[n - i]) {
                    sb.append(n)
                            .append(" = ")
                            .append(i)
                            .append(" + ")
                            .append(n - i)
                            .append("\n");
                    break;
                }
            }

        }

        System.out.println(sb);
    }
}