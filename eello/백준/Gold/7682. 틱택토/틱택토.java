import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String board;
        StringBuilder answer = new StringBuilder();
        while (!"end".equals(board = br.readLine())) {
            answer.append(isValid(board.toCharArray()) ? "valid" : "invalid")
                    .append("\n");
        }

        System.out.print(answer);
    }

    private static boolean isValid(char[] board) {
        int xCnt = 0, oCnt = 0;
        for (char ch : board) {
            if (ch == 'X') {
                xCnt++;
            } else if (ch == 'O') {
                oCnt++;
            }
        }

        if (oCnt > xCnt || Math.abs(xCnt - oCnt) > 1) {
            return false;
        }

        int[] numBoard = new int[board.length];
        for (int i = 0; i < board.length; i++) {
            int elem = 0;

            if (board[i] == 'X') elem = 1;
            else if (board[i] == 'O') elem = -1;

            numBoard[i] = elem;
        }

        boolean x = false, o = false;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> dir = new ArrayList<>();

        list.add(List.of(0, 3, 6));
        dir.add(1);

        list.add(List.of(0, 1, 2));
        dir.add(3);

        list.add(List.of(0));
        dir.add(4);

        list.add(List.of(2));
        dir.add(2);

        for (int i = 0; i < list.size(); i++) {
            boolean[] result = acc(numBoard, new ArrayDeque<>(list.get(i)), dir.get(i));
            x |= result[0];
            o |= result[1];
        }

        if (x & o) return false;
        if (o && xCnt > oCnt) return false;
        if (!x && !o && xCnt + oCnt < 9) return false;
        if (x && xCnt == oCnt) return false;
        return true;
    }

    private static boolean[] acc(int[] board, Queue<Integer> que, int dir) {
        int[] sum = new int[que.size()];
        for (int i = 0; i < 3; i++) {
            int size = que.size();
            for (int j = 0; j < size; j++) {
                int cur = que.poll();
                sum[j] += board[cur];

                int nxt = cur + dir;
                que.add(nxt);
            }
        }

        boolean x = false, o = false;
        for (int v : sum) {
            if (v == 3) x = true;
            else if (v == -3) o = true;
        }

        return new boolean[]{x, o};
    }
}
