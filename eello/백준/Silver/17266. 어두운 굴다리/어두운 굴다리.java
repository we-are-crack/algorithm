import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] pos = new int[m];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int left = 0, right = n;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (validate(n, pos, mid)) {
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }

        System.out.println(answer);
    }

    private static boolean validate(int n, int[] pos, int h) {
        int e = 0;
        for (int p : pos) {
            int left = p - h, right = p + h;
            if (e < left) {
                return false;
            }

            e = right;
        }

        return n <= e;
    }
}
