import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();

        int zero = 0, one = 0;
        for (char ch : s) {
            if (ch == '1') {
                one++;
            } else zero++;
        }

        zero /= 2;
        one /= 2;

        boolean[] removed = new boolean[s.length];
        int left = 0, right = s.length - 1;
        while (left < s.length && right >= 0) {
            if (s[left] == '1' && 0 < one) {
                removed[left] = true;
                one--;
            }

            if (s[right] == '0' && 0 < zero) {
                removed[right] = true;
                zero--;
            }

            left++;
            right--;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < s.length; ++i) {
            if (removed[i]) {
                continue;
            }

            answer.append(s[i]);
        }

        System.out.println(answer);
    }
}
