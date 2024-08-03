import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] d = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int tempMax = 0;
            for(int j = i - 1; j >= 0; j--) {
                if (seq[i] > seq[j])
                    tempMax = Math.max(tempMax, d[j]);
            }
            d[i] = tempMax + 1;
            max = Math.max(max, d[i]);
        }
        System.out.println(max);
    }
}