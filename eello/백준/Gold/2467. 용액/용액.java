import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] seq = new int[n + 1];
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = n - 1, val = Integer.MAX_VALUE;
        int[] answer = new int[2];
        while (l < r) {
            int sum = seq[l] + seq[r];
            if (Math.abs(sum) < val) {
                answer[0] = seq[l];
                answer[1] = seq[r];
                val = Math.abs(sum);
            }

            if (0 < sum) {
                r--;
            } else if (sum < 0) {
                l++;
            } else {
                break;
            }
        }

        System.out.printf("%d %d\n", answer[0], answer[1]);
    }
}
