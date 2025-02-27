import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] seqSize = new int[n + 1];
        int maxSeqSize = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            int tempMaxSize = 0;

            for (int j = 1; j <= num; j++) {
                tempMaxSize = Math.max(tempMaxSize, seqSize[j]);
            }

            seqSize[num] = tempMaxSize + 1;
            maxSeqSize = Math.max(maxSeqSize, seqSize[num]);
        }

        System.out.println(n - maxSeqSize);
    }
}