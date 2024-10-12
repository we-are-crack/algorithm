import java.io.*;
import java.util.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[][] arrMax = new int[n + 1][3];
        int[][] arrMin = new int[n + 1][3];
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());

            arrMax[i][0] = Math.max(arrMax[i - 1][0], arrMax[i - 1][1]) + n1;
            arrMax[i][1] = Math.max(Math.max(arrMax[i - 1][0], arrMax[i - 1][1]), arrMax[i - 1][2]) + n2;
            arrMax[i][2] = Math.max(arrMax[i - 1][1], arrMax[i - 1][2]) + n3;

            arrMin[i][0] = Math.min(arrMin[i - 1][0], arrMin[i - 1][1]) + n1;
            arrMin[i][1] = Math.min(Math.min(arrMin[i - 1][0], arrMin[i - 1][1]), arrMin[i - 1][2]) + n2;
            arrMin[i][2] = Math.min(arrMin[i - 1][1], arrMin[i - 1][2]) + n3;
        }

        System.out.println(Math.max(Math.max(arrMax[n][0], arrMax[n][1]), arrMax[n][2]) + " " + Math.min(Math.min(arrMin[n][0], arrMin[n][1]), arrMin[n][2]));
    }
}