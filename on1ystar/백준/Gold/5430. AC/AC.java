import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String funcString = br.readLine();
            int n = Integer.parseInt(br.readLine());
            StringBuffer sb = new StringBuffer(br.readLine());
            StringTokenizer st = new StringTokenizer(sb.substring(1, sb.length() - 1), ",");

            Deque<String> dq = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                dq.add(st.nextToken());
            }

            ac(dq, funcString);
        }

    }

    private static void ac(Deque<String> dq, String funcString) {
        boolean isReverse = false;

        for (int i = 0; i < funcString.length(); i++) {
            if (funcString.charAt(i) == 'R') {
                isReverse = !isReverse;
            } else {
                String s;
                if (isReverse) {
                    s = dq.pollLast();
                } else {
                    s = dq.pollFirst();
                }

                if (s == null) {
                    System.out.println("error");
                    return;
                }
            }
        }

        if (dq.isEmpty()) {
            System.out.println("[]");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (!dq.isEmpty()) {
                if (isReverse) {
                    sb.append(dq.pollLast());
                } else {
                    sb.append(dq.pollFirst());
                }
                sb.append(",");
            }
            sb.replace(sb.length() - 1, sb.length(), "]");
            System.out.println(sb);
        }
    }
}