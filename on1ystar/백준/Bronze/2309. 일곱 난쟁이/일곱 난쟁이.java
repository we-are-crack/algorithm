import java.io.*;
import java.util.Arrays;

public class Main {

    private static final StringBuilder sb = new StringBuilder();
    private static final int[] arr = new int[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        solve(new boolean[9], -1, 0, 0);

        System.out.println(sb);
    }

    private static boolean solve(boolean[] visited, int now, int cnt, int height) {
        if (cnt == 7 && height == 100) {
            return true;
        }

        for (int i = now + 1; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                boolean found = solve(visited, i, cnt + 1, height + arr[i]);

                if (found) {
                    sb.insert(0, arr[i] + "\n");
                    return true;
                }

                visited[i] = false;
            }
        }

        return false;
    }
}