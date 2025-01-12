import java.io.*;
import java.util.*;

public class Main {

    private static Set<Integer> sum = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] seq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            rec(seq, i, 0);
        }

        int answer = 0, max = n * 100_000;
        for (int i = 1; i <= max; i++) {
            if (!sum.contains(i)) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    private static void rec(int[] seq, int i, int num) {
        num += seq[i];
        sum.add(num);
        for (int j = i + 1; j < seq.length; j++) {
            rec(seq, j, num);
        }
    }
}
