import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int p = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (p-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] seq = new int[20];
            for (int i = 0; i < seq.length; i++) {
                seq[i] = Integer.parseInt(st.nextToken());
            }

            answer.append(n).append(" ").append(lineUp(seq)).append("\n");
        }

        System.out.println(answer);
    }

    private static int lineUp(int[] seq) {
        int step = 0;
        int[] sortedLine = new int[seq.length];

        sortedLine[0] = seq[0];
        for (int i = 1; i < seq.length; i++) {
            int j = i;

            while (--j >= 0 && sortedLine[j] > seq[i]) {
                step++;
                sortedLine[j + 1] = sortedLine[j];
            }

            sortedLine[j + 1] = seq[i];
        }

        return step;
    }
}
