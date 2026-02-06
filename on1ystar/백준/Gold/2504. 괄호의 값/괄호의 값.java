import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        List<Object> stk = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[') {
                stk.add(s.charAt(i));
            } else {
                if (stk.isEmpty()) {
                    System.out.println(0);
                    return;
                }

                int temp = 0;
                Object top = stk.remove(stk.size() - 1);

                while (top instanceof Integer) {
                    temp += (int) top;

                    if (stk.isEmpty()) {
                        System.out.println(0);
                        return;
                    }

                    top = stk.remove(stk.size() - 1);
                }
                if (s.charAt(i) == ')' && (char) top == '(') {
                    stk.add(temp == 0 ? 2 : temp * 2);
                } else if (s.charAt(i) == ']' && (char) top == '[') {
                    stk.add(temp == 0 ? 3 : temp * 3);
                } else {
                    System.out.println(0);
                    return;
                }
            }
        }

        int answer = 0;
        for (Object object : stk) {
            if (!(object instanceof Integer)) {
                answer = 0;
                break;
            }
            
            answer += (int) object;
        }

        System.out.println(answer);
    }
}