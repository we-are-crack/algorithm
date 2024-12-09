import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) { arr[i] = Integer.parseInt(st.nextToken()); }

        for (int i = n - 1; i > 0 ; i--) {
            if (arr[i - 1] < arr[i]) { continue; }

            for (int j = n - 1; j >= i ; j--) {
                if (arr[i - 1] > arr[j]) {
                    swap(arr, j, i - 1);
                    break;
                }
            }

            int start = i;
            int mid = (start + n) / 2;
            int end = n - 1;

            while (start < mid) { swap(arr, start++, end--); }

            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < n; k++) { sb.append(arr[k]).append(" "); }

            System.out.print(sb);
            return;
        }

        System.out.println(-1);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}