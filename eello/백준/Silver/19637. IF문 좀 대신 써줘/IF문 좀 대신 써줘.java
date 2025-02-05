import java.io.*;
import java.util.*;

public class Main {

    private static String[] rating;
    private static int[] power;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 칭호의 개수
        int m = Integer.parseInt(st.nextToken()); // 캐릭터들의 개수

        rating = new String[n];
        power = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            rating[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder answer = new StringBuilder();
        while (m-- > 0) {
            answer.append(determineRating(Integer.parseInt(br.readLine())))
                    .append("\n");
        }

        System.out.print(answer);
    }

    private static String determineRating(int p) {
        int left = 0, right = power.length - 1;
        int rIdx = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (p <= power[mid]) {
                rIdx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return rating[rIdx];
    }
}
