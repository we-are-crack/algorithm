import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            long[] triangles = new long[n < 6 ? 6 : n + 1];
            int[] init = new int[]{1, 1, 1, 2, 2};
            for(int a = 0; a < init.length; a++) triangles[a + 1] = init[a];
            for(int j = 6; j <= n; j++) {
                long triangle1 = triangles[j-1];
                long triangle2 = j - 5 < 1 ? 0 : triangles[j-5];
                triangles[j] = triangle1 + triangle2;
            }
            System.out.println(triangles[n]);
        }
    }
}