import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(hanoi(n, 1, 2, 3));
        System.out.print(sb);
    }

    private static int hanoi(int towerCnt, int from, int mid, int to) {
        if (towerCnt == 1) {
            sb.append(from).append(" ").append(to).append("\n");
            return 1;
        }

        int moveCnt = hanoi(towerCnt - 1, from, to, mid);

        sb.append(from).append(" ").append(to).append("\n");
        moveCnt++;

        moveCnt += hanoi(towerCnt - 1, mid, from, to);

        return moveCnt;
    }
}