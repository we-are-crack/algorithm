import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m, minClick;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Character> brokenButton = new ArrayList<>();

        if (m > 0) {
           st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                brokenButton.add(st.nextToken().charAt(0));
            }
        }

        minClick = Math.abs(n - 100);

        int temp = n;
        while (temp >= 0) {
            if (find(brokenButton, temp)) {
                break;
            }

            temp--;
        }

        temp = n;
        while (++temp <= 999_999) {
            if (find(brokenButton, temp)) {
                break;
            }
        }

        System.out.println(minClick);
    }

    private static boolean find(List<Character> brokenButton, int temp) {
        String tempString = String.valueOf(temp);
        boolean found = true;

        for (int i = 0; i < tempString.length(); i++) {
            if (brokenButton.contains(tempString.charAt(i))) {
                found = false;
                break;
            }
        }

        if (found) {
            minClick = Math.min(minClick, Math.abs(n - temp) + tempString.length());
        }

        return found;
    }
}