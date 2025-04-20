import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] height = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = dfs(height, new int[n], 0, 0);
        StringBuilder answer = new StringBuilder();
        for (int num : result) {
            answer.append(num).append(" ");
        }

        System.out.print(answer);
    }

    private static int[] dfs(int[] height, int[] line, int idx, int visit) {
        if (idx == line.length) {
            return line;
        }

        int[] ret = null;
        for (int i = 1; i < height.length; i++) {
            if (0 < height[i] || (visit & 1 << i) != 0) {
                continue;
            }

            line[idx] = i;
            for (int j = 1; j < i; j++) {
                height[j]--;
            }

            ret = dfs(height, line, idx + 1, visit | 1 << i);
            if (ret != null) {
                break;
            }

            for (int j = 1; j < i; j++) {
                height[j]++;
            }
        }

        return ret;
    }
}
