import java.io.*;
import java.util.*;

public class Main {

    private static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] jewelryArray = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewelryArray[i][0] = Integer.parseInt(st.nextToken());  // 무게
            jewelryArray[i][1] = Integer.parseInt(st.nextToken());  // 가격
        }

        int[] bagArray = new int[k];
        for (int i = 0; i < k; i++) {
            bagArray[i] = Integer.parseInt(br.readLine());  // 각 가방의 최대 무게
        }

        Arrays.sort(jewelryArray, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(bagArray);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long totalValue = 0;
        int jIdx = 0;
        for (int i = 0; i < k; i++) {
            while (jIdx < n && jewelryArray[jIdx][0] <= bagArray[i]) {
                pq.add(jewelryArray[jIdx++][1]);
            }

            if (!pq.isEmpty()) {
                totalValue += pq.remove();
            }
        }

        System.out.println(totalValue);
    }
}