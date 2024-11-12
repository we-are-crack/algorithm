import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 정렬
        Arrays.sort(arr);
        int answer = 0;

        for (int i = 0; i < n; i++) {
            // 투 포인터 사용
            int left = 0;
            int right = n - 1;

            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }
                
                int sum = arr[left] + arr[right];
                
                if (sum == arr[i]) {
                    answer++;
                    break;
                } else if (sum < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}