import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";

        while ((s = br.readLine()) != null) {
            int n = Integer.parseInt(s);
            int num = 1;
            int oneLength = 1;
            int rest = num % n;
            while (rest != 0) {
                if (num / n == 0) {
                    num = num * 10 + 1;
                } else {
                    num = (num % n) * 10 + 1;
                }
                oneLength++;
                rest = num % n;
            }

            System.out.println(oneLength);
        }
    }
}