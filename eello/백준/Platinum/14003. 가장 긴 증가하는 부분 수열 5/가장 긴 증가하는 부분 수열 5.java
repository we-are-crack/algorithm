import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] seq = new int[n];
        int[] dp = new int[n];
        int[] dpIndex = new int[n];
        int[] tracking = new int[n];

        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(tracking, -1);

        int lisLen = 1;
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());

            int insertionIndex = Arrays.binarySearch(dp, seq[i]);
            if (insertionIndex < 0) {
                insertionIndex = -(insertionIndex + 1);
            }

            dp[insertionIndex] = seq[i];
            dpIndex[insertionIndex] = i;
            tracking[i] = insertionIndex == 0 ? -1 : dpIndex[insertionIndex - 1];

            lisLen = Math.max(lisLen, insertionIndex + 1);
        }

        int i = dpIndex[lisLen - 1];
        Deque<Integer> deq = new ArrayDeque<>();
        while(i != -1) {
            deq.addLast(seq[i]);
            i = tracking[i];
        }

        System.out.println(lisLen);
        StringBuilder sb = new StringBuilder();
        while (!deq.isEmpty()) {
            sb.append(deq.pollLast()).append(" ");
        }
        System.out.println(sb);
    }
}
