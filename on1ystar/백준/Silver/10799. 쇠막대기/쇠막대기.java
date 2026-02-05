import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<Character> stk = new ArrayList<>();
        boolean isLaser = true;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.add(s.charAt(i));
                isLaser = true;
            } else {
                stk.remove(stk.size() - 1);

                if (isLaser) {
                    count += stk.size();
                    isLaser = false;
                } else {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}