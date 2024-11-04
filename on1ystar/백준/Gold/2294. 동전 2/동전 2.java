import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Set<Integer> coins = new HashSet<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        Integer[] coinsArray = coins.toArray(new Integer[coins.size()]);
        Arrays.sort((coinsArray));

        int[] d = new int[k + 1];
        Arrays.fill(d, 10_001);
        for (int i = 1; i < k + 1; i++) {
            for (int coin : coinsArray) {
                if (i < coin) {
                    break;
                } else if (i == coin) {
                    d[i] = 1;
                } else {
                    if (d[i - coin] != 10_001) {
                        d[i] = Math.min(d[i], d[i - coin] + 1);
                    }
                }
            }
        }

        System.out.println(d[k] == 10_001 ? -1 : d[k]);
    }
}