import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            char[] input = br.readLine().toCharArray();

            int s = 0;
            for (char ch : input) {
                if (ch == '(') {
                    s++;
                } else {
                    s--;
                    if (s < 0) {
                        break;
                    }
                }
            }

            answer.append(s == 0 ? "YES" : "NO").append("\n");
        }

        System.out.println(answer);
    }
}
