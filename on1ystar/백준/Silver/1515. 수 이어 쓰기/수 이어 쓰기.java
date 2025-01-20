import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        int i = 1;
        int nIdx = 0;
        while (nIdx < n.length()) {
            String s = String.valueOf(i);

            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == n.charAt(nIdx)) {
                    nIdx++;
                }

                if (nIdx == n.length()) {
                    break;
                }
            }

            i++;
        }

        System.out.println(i - 1);
    }
}