import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a, 0});
        while (!q.isEmpty()) {
            int[] numAndCnt = q.poll();
            if (numAndCnt[0] == b) {
                answer = numAndCnt[1] + 1;
                break;
            }

            long[] nextNums = new long[]{numAndCnt[0] * 2L, numAndCnt[0] * 10L + 1L};
            for (long nextNum : nextNums) {
                if (nextNum <= b) {
                    q.offer(new int[] {(int) nextNum, numAndCnt[1] + 1});
                }
            }
        }

        System.out.print(answer == 0 ? -1 : answer);
    }
}