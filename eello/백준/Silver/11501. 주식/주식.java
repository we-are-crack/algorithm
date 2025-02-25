import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] stock = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                stock[i] = Integer.parseInt(st.nextToken());
            }

            long maxVal = 0;
            int max = stock[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                if (stock[i] > max) {
                    max = stock[i];
                } else {
                    maxVal += max - stock[i];
                }
            }

            answer.append(maxVal).append("\n");
        }

        System.out.print(answer);
    }
}
