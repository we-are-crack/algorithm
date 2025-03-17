import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Boolean> names = new HashMap<>(n);
        PriorityQueue<String> pq = new PriorityQueue<>(Math.min(n, m));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            names.put(br.readLine(), false);
        }

        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            if (names.get(name) != null) {
                pq.add(name);
            }
        }
        System.out.println(pq.size());

        while (!pq.isEmpty()) {
            sb.append(pq.remove()).append("\n");
        }
        System.out.print(sb);
    }
}