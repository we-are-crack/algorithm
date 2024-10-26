import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] d = new int[n];
        d[n - 1] = -1;
        for (int i = n - 2; i >= 0; i--) {

            int j = i + 1;
            while (true) {
                if (arr[j] > arr[i]) {
                    d[i] = j;
                    break;
                } 
                
                if (d[j] == -1) {
                    d[i] = -1;
                    break;
                }
                
                j = d[j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (d[i] == -1) {
                sb.append(-1);
            } else {
                sb.append(arr[d[i]]);
            }

            sb.append(" ");
        }

        System.out.print(sb);
    }
}