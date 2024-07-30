import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] rgbs = new int[n + 1][3];
        for(int i = 1; i <= n; i++) {
            String[] rgb = br.readLine().split(" ");
            rgbs[i][0] = Integer.parseInt(rgb[0]) + Math.min(rgbs[i-1][1], rgbs[i-1][2]);
            rgbs[i][1] = Integer.parseInt(rgb[1]) + Math.min(rgbs[i-1][0], rgbs[i-1][2]);
            rgbs[i][2] = Integer.parseInt(rgb[2]) + Math.min(rgbs[i-1][0], rgbs[i-1][1]);
        }
        System.out.println(Math.min(Math.min(rgbs[n][0], rgbs[n][1]), rgbs[n][2]));;
    }
}