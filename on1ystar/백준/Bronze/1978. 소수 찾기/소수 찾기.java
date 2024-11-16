import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 1) {
                continue;
            }

            boolean prime = true;

            for (int j = 2; j <= Math.sqrt(num); j++) {
                if (num % j == 0) {
                    prime = false;
                }
            }

            if (prime) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}