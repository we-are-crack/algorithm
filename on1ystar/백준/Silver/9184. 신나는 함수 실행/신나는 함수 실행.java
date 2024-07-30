import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][][] d = new int[21][21][21];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]), b = Integer.parseInt(line[1]), c = Integer.parseInt(line[2]);
            if(a == -1 && b == -1 && c == -1) return;
            System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));
        }
    }
    private static int w(int a, int b, int c) {
        if(a <= 0 || b <= 0 || c <= 0) return 1;
        else if(a > 20 || b > 20 || c > 20) return w(20, 20, 20);
        else if(a < b && b < c) {
            int n1 = d[a][b][c-1] == 0 ? w(a, b, c - 1) : d[a][b][c-1];
            int n2 = d[a][b-1][c-1] == 0 ? w(a,b - 1, c - 1) : d[a][b-1][c-1];
            int n3 = d[a][b-1][c] == 0 ? w(a, b - 1, c) : d[a][b-1][c];
            d[a][b][c] = n1 + n2 - n3;
            return d[a][b][c];
        }
        else {
            int n1 = d[a-1][b][c] == 0 ? w(a - 1, b, c) : d[a-1][b][c];
            int n2 = d[a-1][b-1][c] == 0 ? w(a - 1, b - 1, c) : d[a-1][b-1][c];
            int n3 = d[a-1][b][c-1] == 0 ? w(a - 1, b, c - 1) : d[a-1][b][c-1];
            int n4 = d[a-1][b-1][c-1] == 0 ? w(a - 1, b - 1, c - 1) : d[a-1][b-1][c-1];
            d[a][b][c] = n1 + n2 + n3 - n4;
            return d[a][b][c];
        }
    }
}