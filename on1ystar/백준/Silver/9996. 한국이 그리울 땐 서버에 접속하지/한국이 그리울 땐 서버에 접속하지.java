import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String reg = br.readLine().replaceAll("\\*", "\\\\w*");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (br.readLine().replaceFirst(reg, "").equals("")) {
                sb.append("DA");
            } else {
                sb.append("NE");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}