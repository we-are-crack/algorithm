import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            maxLength = Math.max(maxLength, words[i].length());
        }

        int left = 1, right = maxLength;
        String prefix = "";
        while (left <= right) {
            int mid = (left + right) / 2;
            String temp = getCommonPrefix(words, mid);
            
            if (temp.isEmpty()) {
                right = mid - 1;
            } else {
                prefix = temp;
                left = mid + 1;
            }
        }

        StringBuilder answer = new StringBuilder();
        int cnt = 0 ;

        for (int i = 0; i < n && cnt < 2; i++) {
            if (words[i].startsWith(prefix)) {
                answer.append(words[i]).append("\n");
                cnt++;
            }
        }

        System.out.print(answer);
    }

    private static String getCommonPrefix(String[] words, int len) {
        Set<String> set = new HashSet<>();
        String commonPrefix = "";
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].length() < len) {
                continue;
            }

            String prefix = words[i].substring(0, len);
            if (!set.add(prefix)) {
                commonPrefix = prefix;
            }
        }

        return commonPrefix;
    }
}
