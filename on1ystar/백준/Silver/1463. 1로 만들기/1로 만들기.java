import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> q = new ArrayDeque<>();
        int[] visited = new int[n + 1];
        q.add(n);

        while (!q.isEmpty()) {
            int i = q.removeFirst();

            if (i == 1) {
                System.out.println(visited[1]);
                break;
            }

            if (i % 3 == 0 && visited[i / 3] == 0) {
                visited[i / 3] = visited[i] + 1;
                q.addLast(i / 3);
            }

            if (i % 2 == 0 && visited[i / 2] == 0) {
                visited[i / 2] = visited[i] + 1;
                q.addLast(i / 2);
            }

            if (visited[i - 1] == 0) {
                visited[i - 1] = visited[i] + 1;
                q.addLast(i - 1);
            }
        }
    }
}