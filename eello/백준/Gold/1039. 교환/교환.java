import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] arr = st.nextToken().toCharArray();
        int k = Integer.parseInt(st.nextToken());

        int answer = -1;
        if (isPossible(arr)) {
            answer = sort(arr, k, 0);
        }

        System.out.println(answer);
    }

    private static boolean isPossible(char[] arr) {
        if (arr.length == 1) {
            return false;
        } else if (arr.length == 2) {
            return arr[1] != '0';
        }

        return true;
    }

    private static int sort(char[] arr, int k, int idx) {
        if (k == 0) {
            return Integer.parseInt(String.valueOf(arr));
        }

        if (idx == arr.length - 1) {
            if (k % 2 == 1 && !hasSameNumber(arr)) {
                swap(arr, idx - 1, idx);
            }

            return Integer.parseInt(String.valueOf(arr));
        }

        List<Integer> maxIndexes = getMaxIndexes(arr, idx);
        if (!maxIndexes.isEmpty()) {
            int ret = -1;
            for (Integer mi : maxIndexes) {
                char[] copy = Arrays.copyOf(arr, arr.length);
                swap(copy, idx, mi);
                ret = Math.max(ret, sort(copy, k - 1, idx + 1));
            }

            return ret;
        } else {
            return sort(arr, k, idx + 1);
        }
    }

    private static List<Integer> getMaxIndexes(char[] arr, int cur) {
        List<Integer> indexes = new ArrayList<>();
        Character max = max(arr, cur);
        if (max == null) {
            return indexes;
        }

        for (int i = cur + 1; i < arr.length; i++) {
            if (max <= arr[i]) {
                indexes.add(i);
            }
        }

        return indexes;
    }

    private static Character max(char[] arr, int cur) {
        char max = arr[cur];
        for (int i = cur + 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max == arr[cur] ? null : max;
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean hasSameNumber(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
