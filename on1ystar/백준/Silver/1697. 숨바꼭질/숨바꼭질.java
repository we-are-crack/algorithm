import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        if (start >= target) {
            System.out.println(start - target);
            return;
        }

        int second = 1;
        int candidateMinSecond = 100_000;
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[100_001];

        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            if (second >= candidateMinSecond) {
                System.out.println(candidateMinSecond);
                return;
            }
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int now = q.removeFirst();
                int[] nextArr = new int[]{now - 1, now + 1, now * 2};

                for (int next : nextArr) {
                    if (next == target) {
                        System.out.println(second);
                        return;
                    }

                    if (0 < next && next < target && !visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    } else if (next > target) {
                        candidateMinSecond = Math.min(candidateMinSecond, next - target + second);
                    }
                }
            }

            second++;
        }
    }
}