import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int startIndex = 0;
        int sum = 0;
        int minLength = 100_000;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];

            if (sum < s) {
                if (minLength != 100_000) {
                    sum -= arr[startIndex++];
                }

                continue;
            }

            while (sum >= s) {
                sum -= arr[startIndex];
                startIndex++;
            }

            minLength = i - (startIndex- 1) + 1;
        }

        System.out.print(minLength == 100_000 ? 0 : minLength);
    }
}