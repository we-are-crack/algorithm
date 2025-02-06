import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> keyword = new HashSet<>();
        for (int i = 0; i < n; i++) {
            keyword.add(br.readLine());
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while (st.hasMoreTokens()) {
                n -= keyword.remove(st.nextToken()) ? 1 : 0;
            }

            answer.append(n).append("\n");
        }

        System.out.print(answer);
    }
}
