import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int initTime;
    private static int trigger;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        List<Integer> targetSeq = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            targetSeq.add(Integer.parseInt(st.nextToken()));
        }

        permutation(targetSeq, new ArrayList<>(), new boolean[n + 1]);

        if (trigger != 2) {
            System.out.println(-1);
        }

    }

    private static void permutation(List<Integer> targetSeq,
                                    List<Integer> tempSeq,
                                    boolean[] visited) {
        if (tempSeq.size() == n) {
            if (trigger == 0) {
                trigger = 1;
            } else {
                StringBuilder sb = new StringBuilder();

                for (int num : tempSeq) {
                    sb.append(num).append(" ");
                }

                System.out.print(sb);
                trigger = 2;
            }

            return;
        }

        for (int i = initTime != targetSeq.size() ? targetSeq.get(initTime++) : 1; i <= n; i++) {
            if (trigger == 2) {
                break;
            }

            if (!visited[i]) {
                visited[i] = true;
                tempSeq.add(i);
                permutation(targetSeq, tempSeq, visited);
                tempSeq.remove(tempSeq.size() - 1);
                visited[i] = false;
            }
        }
    }
}