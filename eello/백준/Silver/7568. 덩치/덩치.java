import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] physical = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            physical[i][0] = Integer.parseInt(st.nextToken());
            physical[i][1] = Integer.parseInt(st.nextToken());
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int rank = 1;

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                rank += compare(physical[i], physical[j]);
            }

            answer.append(rank).append(" ");
        }

        System.out.println(answer);
    }

    /**
     * @return 덩치가 p1 < p2 이면 1, 비교할 수 없거나 더 작은 경우 0
     */
    private static int compare(int[] p1, int[] p2) {
        if (p1[0] < p2[0] && p1[1] < p2[1]) {
            return 1;
        }

        return 0;
    }
}
