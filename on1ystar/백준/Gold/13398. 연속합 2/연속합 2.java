import java.io.*;
import java.util.*;

public class Main {

    private static final int MIN_VALUE = -1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n + 1];
        int[][] d = new int[n + 1][2];
        int answer = MIN_VALUE;
        arr[0] = MIN_VALUE;
        d[0][0] = MIN_VALUE;
        d[0][1] = MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            d[i][0] = Math.max(arr[i] + d[i - 1][0], arr[i]);
            d[i][1] = Math.max(arr[i] + d[i - 1][1], d[i - 1][0]);
            answer = Math.max(answer, Math.max(d[i][0], d[i][1]));
        }

        System.out.println(answer);

//        for (int i = 1; i <= n; i++) {
//            System.out.println("d[" + i + "] = " + Arrays.toString(d[i]));
//        }

    }
}