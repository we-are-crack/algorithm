import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, score, p;
        n = Integer.parseInt(st.nextToken());
        score = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
            return;
        }

        int higher = 0, same = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int s = Integer.parseInt(st.nextToken());
            if (s > score) {
                higher++;
            } else if (s == score) {
                same++;
            }
        }

        System.out.println(higher + same < p ? higher + 1 : -1);
    }
}
