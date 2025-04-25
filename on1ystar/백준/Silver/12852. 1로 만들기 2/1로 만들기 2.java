import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] d = new int[n + 1][2];  //{from, min}
        Deque<Integer> q = new ArrayDeque<>();

        q.add(1);

        while (!q.isEmpty()) {
            int num = q.removeFirst();

            if (num == n) {
                System.out.println(d[n][1]);
                break;
            }

            if (num * 3 <= n && d[num * 3][0] == 0) {
                d[num * 3][0] = num;
                d[num * 3][1] = d[num][1] + 1;
                q.add(num * 3);
            }

            if (num * 2 <= n && d[num * 2][0] == 0) {
                d[num * 2][0] = num;
                d[num * 2][1] = d[num][1] + 1;
                q.add(num * 2);
            }

            if (d[num + 1][0] == 0) {
                d[num + 1][0] = num;
                d[num + 1][1] = d[num][1] + 1;
                q.add(num + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = n;
        while (i != 0) {
            sb.append(i).append(" ");
            i = d[i][0];
        }

        System.out.print(sb);
    }
}