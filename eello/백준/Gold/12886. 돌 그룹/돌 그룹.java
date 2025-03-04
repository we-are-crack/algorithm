import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        boolean[][] visit = new boolean[1501][1501];

        System.out.print(isPossible(a, b, c, visit) ? 1 : 0);
    }

    private static boolean isPossible(int a, int b, int c, boolean[][] visit) {
        int[] stone = {a, b, c};
        Arrays.sort(stone);

        if (visit[stone[0]][stone[1]]) {
            return false;
        }

        visit[stone[0]][stone[1]] = true;
        if (stone[0] == stone[1] && stone[1] == stone[2]) {
            return true;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (stone[i] == stone[j]) {
                    continue;
                }

                int x = stone[i], y = stone[j];
                stone[i] = x + x;
                stone[j] = y - x;

                int total = a + b + c;
                if (isPossible(stone[i], stone[j], total - (x + y), visit)) {
                    return true;
                }

                stone[i] = x;
                stone[j] = y;
            }
        }

        return false;
    }
}
