import java.io.*;

public class Main {

    private static final int MAX = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] arr = new long[MAX];

        for (int i = 1; i < MAX; i++) {
            for (int j = i; j < MAX; j += i) {
                arr[j] += i;
            }
        }

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        long[] d = new long[MAX];

        for (int i = 1; i < MAX; i++) {
            d[i] = arr[i] + d[i - 1];
        }

        for (int i = 0; i < t; i++) {
            sb.append(d[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.println(sb);
    }
}