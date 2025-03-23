import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long[] sumTwoNum = new long[n * (n - 1) / 2];
        int idx = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n - 1; j++) {
                sumTwoNum[idx++] = ((long) arr[i] + arr[j]);
            }
        }

        Arrays.sort(sumTwoNum);

        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                int target = arr[i] - arr[j];
                if (bs(sumTwoNum, target)) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }

    private static boolean bs(long[] sumTwoNum, int target) {
        int start = 0;
        int end = sumTwoNum.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (sumTwoNum[mid] < target) {
                start = mid + 1;
            } else if (sumTwoNum[mid] > target) {
                end = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}