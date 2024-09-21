import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static int[][] z = {{0, 1}, {2, 3}};
    private static int r;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        find(n,0, 0, 0);
    }

    private static void find(int n, int baseR, int baseC, int start) {
        if (n == 0) {
            System.out.println(start);
            return;
        }

        int half = (int) Math.pow(2, n - 1);
        int size = (int) Math.pow(2, n * 2);

        if (r < baseR + half && c < baseC + half) {
            find(n - 1, baseR, baseC, start);
        } else if (r < baseR + half && baseC + half <= c) {
            find(n - 1, baseR, baseC + half, start + (size / 4));
        } else if (baseR + half <= r && c < baseC + half) {
            find(n - 1, baseR + half, baseC, start + (size / 2));
        } else {
            find(n - 1, baseR + half, baseC + half, start + (size / 2) + (size / 4));
        }
    }
}