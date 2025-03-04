import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, l;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] stars = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        int maxCnt = 1;
        for (int i = 0; i < k; i++) {
            int[] star1 = stars[i];
            for (int j = i + 1; j < k; j++) {
                int[] star2 = stars[j];
                int[] startPos = new int[]{Math.min(star1[0], star2[0]), Math.min(star1[1], star2[1])};
                int[] endPos = new int[]{Math.max(star1[0], star2[0]), Math.max(star1[1], star2[1])};

                if (l < endPos[0] - startPos[0] || l < endPos[1] - startPos[1]) {
                    continue;
                }

                int cnt = 0;
                for (int[] star : stars) {
                    if (valid(startPos, star)) {
                        cnt++;
                    }
                }

                maxCnt = Math.max(maxCnt, cnt);
            }
        }

        System.out.println(k - maxCnt);
    }

    private static boolean valid(int[] startPos, int[] pos) {
        return startPos[0] <= pos[0] && pos[0] <= startPos[0] + l && startPos[1] <= pos[1] && pos[1] <= startPos[1] + l;
    }
}