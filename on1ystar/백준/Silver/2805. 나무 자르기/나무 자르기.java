import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Integer[] trees = new Integer[n];
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, trees[i]);
        }

        int minHeight = 0;
        while (maxHeight >= minHeight) {
            int cutHeight = (maxHeight + minHeight) / 2;
            long totalCutHeight = cut(trees, cutHeight);

            if (totalCutHeight >= m) {
                minHeight = cutHeight + 1;
            } else {
                maxHeight = cutHeight - 1;
            }
        }

        System.out.println(maxHeight);
    }

    private static long cut(Integer[] trees, int cutHeight) {
        long total = 0;
        for (Integer tree : trees) {
            if (tree - cutHeight > 0) {
                total += tree - cutHeight;
            }
        }

        return total;
    }
}