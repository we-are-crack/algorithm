import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[] input = br.readLine().toCharArray();

        boolean[] state = new boolean[n]; // state[i] = true => 사람이거나 먹힌 상태 / false => 먹을 수 있는 상태
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'P') {
                state[i] = true;
            }
        }
        
        int answer = 0;
        for (int i = 0; i < state.length; i++) {
            if (input[i] == 'H') {
                continue;
            }

            int start = Math.max(0, i - k), end = Math.min(n - 1, i + k);
            for (int j = start; j <= end; j++) {
                if (state[j]) {
                    continue;
                }

                state[j] = true;
                answer++;
                break;
            }
        }

        System.out.println(answer);
    }
}
