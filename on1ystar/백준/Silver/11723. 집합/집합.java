import java.io.*;
import java.util.*;

public class Main {

    private static final Set<Integer> mySet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = Integer.parseInt(st.hasMoreTokens() ? st.nextToken() : "0");

            switch (command) {
                case "add" : {
                    mySet.add(num);
                    break;
                }
                case "remove" : {
                    mySet.remove(num);
                    break;
                }
                case "check" : {
                    if (mySet.contains(num)) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                }
                case "toggle" : {
                    if (mySet.contains(num)) mySet.remove(num);
                    else mySet.add(num);
                    break;
                }
                case "all" : {
                    mySet.clear();
                    for (int j = 1; j <= 20; j++) {
                        mySet.add(j);
                    }
                    break;
                }
                case "empty" : mySet.clear();
            }
        }

        System.out.print(sb);
    }
}