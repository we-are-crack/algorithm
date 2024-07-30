import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] seq = br.readLine().split(" ");
        int[] d = new int[n];
        d[0] = Integer.parseInt(seq[0]);
        for(int i = 1; i < n; i++) {
            d[i] = Math.max(Integer.parseInt(seq[i]), Integer.parseInt(seq[i]) + d[i - 1]);
        }
        Arrays.stream(d).max().ifPresent(System.out::println);
    }
}