import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            y = n == y ? 0 : y;
            boolean[] restArr = new boolean[n];

            while (true) {
                int rest = x % n;

                if (rest == y) {
                    sb.append(x).append("\n");
                    break;
                }

                if (restArr[rest]) {
                    sb.append(-1).append("\n");
                    break;
                }

                restArr[rest] = true;
                x += m;
            }
        }

        System.out.print(sb);
    }
}