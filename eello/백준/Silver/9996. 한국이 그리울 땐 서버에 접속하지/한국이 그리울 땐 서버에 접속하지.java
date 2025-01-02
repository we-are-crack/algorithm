import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), "*");
        String regex = "^" + st.nextToken() + "[a-z]*" + st.nextToken() + "$";
        Pattern pattern = Pattern.compile(regex);
        
        StringBuilder answer = new StringBuilder();
        while (n-- > 0) {
            String file = br.readLine();
            answer.append(pattern.matcher(file).matches() ? "DA" : "NE")
                    .append("\n");
        }

        System.out.println(answer);
    }
}
