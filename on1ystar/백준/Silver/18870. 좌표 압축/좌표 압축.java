import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] sortingArr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sortingArr[i] = arr[i];
        }

        Arrays.sort(sortingArr);

        List<Integer> sortedList = new ArrayList<>();
        sortedList.add(sortingArr[0]);
        for (int i = 1; i < n; i++) {
            if (sortedList.get(sortedList.size() - 1) != sortingArr[i]) {
                sortedList.add(sortingArr[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = sortedList.size() - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (arr[i] > sortedList.get(mid)) {
                    start = mid + 1;
                } else if (arr[i] < sortedList.get(mid)) {
                    end = mid - 1;
                } else {
                    sb.append(mid).append(" ");
                    break;
                }
            }
        }

        System.out.print(sb);
    }
}