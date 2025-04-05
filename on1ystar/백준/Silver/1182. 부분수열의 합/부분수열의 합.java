import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    private static int n, s, answer;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        find(0, 0);
        System.out.println(answer);
    }

    private static void find(int pos, int sum) {
        if (pos == n) {
            return;
        }

        for (int i = pos; i < n; i++) {
            if (sum + arr[i] == s) {
                answer++;
            }
            find(i + 1, sum + arr[i]);
        }
    }
}