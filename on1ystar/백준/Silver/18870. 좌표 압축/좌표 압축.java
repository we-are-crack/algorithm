import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[][] compArr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            compArr[i] = new int[]{arr[i], i};
        }

        Arrays.sort(compArr, Comparator.comparingInt(a -> a[0]));

        int compNum = 0;
        arr[compArr[0][1]] = compNum;
        for (int i = 1; i < n; i++) {
            if (compArr[i][0] != compArr[i - 1][0]) {
                compNum++;
            }

            arr[compArr[i][1]] = compNum;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.print(sb);
    }
}