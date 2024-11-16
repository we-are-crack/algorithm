import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        if (n1 >= n2) {
            System.out.println(getCommonDivisor(n1, n2));;
            System.out.println(getCommonMultiple(n1, n2));;
        } else {
            System.out.println(getCommonDivisor(n2, n1));;
            System.out.println(getCommonMultiple(n2, n1));;
        }
    }

    private static int getCommonDivisor(int large, int small) {
        for (int i = small; i >= 2; i--) {
            if (small % i == 0 && large % i == 0) {
                return i;
            }
        }

        return 1;
    }

    private static int getCommonMultiple(int large, int small) {
        for (int i = 1; i < large; i++) {
            if ((small * i) % large == 0) {
                return small * i;
            }
        }

        return small * large;
    }
}