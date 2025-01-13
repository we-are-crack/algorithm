import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer dist = new StringTokenizer(br.readLine());
        StringTokenizer price = new StringTokenizer(br.readLine());

        long answer = 0, minPrice = Long.MAX_VALUE;
        while (dist.hasMoreTokens()) {
            int nd = Integer.parseInt(dist.nextToken());
            int p = Integer.parseInt(price.nextToken());

            minPrice = Math.min(minPrice, p);
            answer += nd * minPrice;
        }

        System.out.println(answer);
    }
}
