import java.io.*;
import java.util.*;

public class Main {

    private static final Map<String, Integer> player = new HashMap<>();

    static {
        player.put("Y", 2);
        player.put("F", 3);
        player.put("O", 4);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = player.get(st.nextToken()) - 1;

        Set<String> applicants = new HashSet<>();
        for (int i = 0; i < n; i++) {
            applicants.add(br.readLine());
        }

        System.out.println(applicants.size() / p);
    }
}
