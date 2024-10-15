import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {i, Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        String[] compress = new String[n];
        compress[arr[0][0]] = "0";
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i][1] == arr[i - 1][1]) {
                cnt++;
            }

            compress[arr[i][0]] = String.valueOf(i - cnt);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(compress[i]);
            sb.append(" ");
        }

        System.out.print(sb);
    }
}