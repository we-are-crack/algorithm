import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] seq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(seq);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                int target = seq[i] - seq[j];
                boolean half = target == seq[j];

                int idx = indexOf(seq, target);
                if (idx == i || idx == j) {
                    if (idx < n - 1 && seq[idx] == seq[idx + 1]) {
                        idx++;
                    } else continue;
                }

                if (idx != -1) {
                    if (half) {
                        if (idx < n - 1 && seq[idx] == seq[idx + 1]) {
                            answer++;
                            break;
                        }
                    } else {
                        answer++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static int indexOf(int[] seq, int target) {
        int start = 0, end = seq.length - 1, index = -1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (target <= seq[mid]) {
                if (seq[mid] == target) {
                    index = mid;
                }

                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return index;
    }
}
