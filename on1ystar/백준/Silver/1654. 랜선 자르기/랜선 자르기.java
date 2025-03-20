import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] lans = new int[k];
        long maxLength = 0;
        for (int i = 0; i < k; i++) {
            lans[i] = Integer.parseInt(br.readLine());
            maxLength = Math.max(maxLength, lans[i]);
        }

        long minLength = 1;
        while (maxLength >= minLength) {
            long midLength = (maxLength + minLength) / 2;
            long lanCnt = cut(lans, midLength);

            if (lanCnt >= n) {
                minLength = midLength + 1;
            } else {
                maxLength = midLength - 1;
            }
        }

        System.out.println(maxLength);
    }

    private static long cut(int[] LANs, long length) {
        long lanCnt = 0;
        for (int lan : LANs) {
            lanCnt += lan / length;
        }

        return lanCnt;
    }
}