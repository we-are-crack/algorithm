import java.io.*;
import java.util.*;

public class Main {

    private static final int MIN_VALUE = -1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[][] d = new int[n + 1][2];
        int answer = MIN_VALUE;
        d[0][0] = MIN_VALUE;
        d[0][1] = MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            
            if (d[i - 1][0] > 0) {
                d[i][0] = num + d[i - 1][0];
            } else {
                d[i][0] = num;
            }
            
            d[i][1] = Math.max(num + d[i - 1][1], d[i - 1][0]);
            answer = Math.max(answer, Math.max(d[i][0], d[i][1]));
        }

        System.out.println(answer);
    }
}