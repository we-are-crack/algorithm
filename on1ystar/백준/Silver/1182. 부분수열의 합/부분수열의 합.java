import java.io.*;
import java.util.*;

public class Main {

    private static int n, s, answer;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            solve(i + 1, arr[i]);
        }

        System.out.println(answer);
    }

    private static void solve(int nowIdx, int sum) {
        if (sum == s) {
            answer++;
        }

        for (int i = nowIdx; i < n; i++) {
            solve(i + 1, sum + arr[i]);
        }
    }
}