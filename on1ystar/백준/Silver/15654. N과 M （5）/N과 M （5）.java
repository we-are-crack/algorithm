import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n, m;
    private static int[] numbers;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        solve(new int[m], 0, new boolean[n]);
        bw.close();
    }

    private static void solve(int[] sequence, int cnt, boolean[] visited) throws IOException  {
        if (cnt == m) {

            for (int i = 0; i < m; i++) {
                bw.write(sequence[i] + " ");
            }
            bw.write("\n");
            bw.flush();
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[cnt] = numbers[i];
                solve(sequence, cnt + 1, visited);
                visited[i] = false;
            }
        }
    }
}