import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int i = 0, n = input.length(), answer = 0;
        while (i < n) {
            for (char ch : String.valueOf(++answer).toCharArray()) {
                if (i < n && input.charAt(i) == ch) {
                    i++;
                }
            }
        }

        System.out.println(answer);
    }
}
