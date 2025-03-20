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
        Integer[] trees = new Integer[n + 1];
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        trees[n] = 0;

        Arrays.sort(trees, Comparator.reverseOrder());

        int cutTreeCnt = 1;
        int cutHeight = trees[0];
        int totalCutTreeHeight = 0;
        for (int i = 0; i < n; i++) {
            long nextCuttingHeight = trees[i] - trees[i + 1];
            if (nextCuttingHeight * cutTreeCnt >= (m - totalCutTreeHeight)) {
                System.out.println(cutHeight - ((int) Math.ceil((m - totalCutTreeHeight) / (double) cutTreeCnt)));
                break;
            }

            totalCutTreeHeight += nextCuttingHeight * cutTreeCnt;
            cutHeight -= nextCuttingHeight;
            cutTreeCnt++;
        }
    }
}