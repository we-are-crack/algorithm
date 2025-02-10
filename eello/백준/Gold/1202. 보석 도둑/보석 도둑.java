import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] jewels = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(bags);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a ,b) -> b[1] - a[1]);
        int ji = 0;
        long answer = 0;
        for (int i = 0; i < k; i++) {
            while (ji < jewels.length && jewels[ji][0] <= bags[i]) {
                pq.offer(jewels[ji++]);
            }

            answer += pq.isEmpty() ? 0 : pq.poll()[1];
        }

        System.out.println(answer);
    }
}
