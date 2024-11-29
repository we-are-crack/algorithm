import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int l = s.length();
        int d = (int) Math.pow(10, l - 1);
        int n = Integer.parseInt(s);
        long answer = 0L;

        answer += (long) ((n / d) - 1) * d * l;
        answer += (long) ((n % d) + 1) * l;

        while (l-- > 1) {
            d /= 10;
            answer += (long) d * 9 * l;
        }

        System.out.println(answer);
    }
}