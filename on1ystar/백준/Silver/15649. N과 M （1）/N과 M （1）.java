import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static int n, m;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        permu(new StringBuilder(), 0, new boolean[n + 1]);
        System.out.print(answer);
    }

    private static void permu(StringBuilder sb, int length, boolean[] visited) {
        if (length == m) {
            answer.append(sb).append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(i).append(" ");
                permu(sb, length + 1, visited);
                sb.delete(sb.length() - 2, sb.length());
                visited[i] = false;
            }
        }
    }
}