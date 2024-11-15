import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = 0;

        for (int i = 1; i <= Math.sqrt(n); i++) {
            answer += i;
            for (int j = i + 1; j <= n; j++) {
                if (i * j <= n) {
                    answer += i + j;
                } else {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}