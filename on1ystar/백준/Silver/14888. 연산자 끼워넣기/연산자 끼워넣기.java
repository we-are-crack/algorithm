import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] numbers;
    static int[] ops;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        ops = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            numbers[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++)
            ops[i] = Integer.parseInt(st.nextToken());
        dfs(numbers[0], 1, 0);
        System.out.println(max);
        System.out.println(min);
    }
    static void dfs(int n1, int n2Index, int cnt) {
        if(cnt == n - 1) {
            max = Math.max(max, n1);
            min = Math.min(min, n1);
            return;
        }
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < ops[i]; j++) {
                if(ops[i] != 0) {
                    ops[i]--;
                    dfs(calculate(n1, numbers[n2Index], i), n2Index + 1, cnt + 1);
                    ops[i]++;
                }
            }
        }
    }
    static int calculate(int n1, int n2, int op) {
        switch (op) {
            case 0: return n1 + n2;
            case 1: return n1 - n2;
            case 2: return n1 * n2;
            case 3: return n1 / n2;
            default: return 0;
        }
    }
}