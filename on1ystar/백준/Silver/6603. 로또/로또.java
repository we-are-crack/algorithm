import java.io.*;
import java.util.*;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            if (k == 0) {
                break;
            }

            int[] numbers = new int[k];
            for (int i = 0; i < k; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            doLotto(numbers, new ArrayList<>(), 0);

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void doLotto(int[] numbers, List<Integer> lotto, int start) {
        if (lotto.size() == 6) {
            for (int num : lotto) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            lotto.add(numbers[i]);
            doLotto(numbers, lotto, i + 1);
            lotto.remove(lotto.size() - 1);
        }
    }
}