import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = br.readLine();

            if (s.equals("end")) {
                System.out.print(sb);
                return;
            }

            if (isValid(s)) {
                sb.append("valid");
            } else {
                sb.append("invalid");
            }
            sb.append("\n");
        }
    }

    public static boolean isValid(String board) {
        int xCnt = 0;
        int oCnt = 0;
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == 'X') {
                xCnt++;
            } else if (board.charAt(i) == 'O'){
                oCnt++;
            }
        }

        if (xCnt == oCnt) {
            // o만 이겨야 함
            return !checkWin(board, 'X') && checkWin(board, 'O');
        } else if (xCnt == oCnt + 1) {
            // x만 이기거나 판이 꽉 참
            return !checkWin(board, 'O') && (checkWin(board, 'X') || xCnt + oCnt == 9);
        }

        return false;
    }

    public static boolean checkWin(String board, char c) {
        for (int i = 0; i < 3; i++) {
            if (board.charAt(i) == c && board.charAt(i + 3) == c && board.charAt(i + 6) == c) {
                return true;
            }
            if (board.charAt(i * 3) == c && board.charAt(i * 3 + 1) == c && board.charAt(i * 3 + 2) == c) {
                return true;
            }
        }

        if (board.charAt(0) == c && board.charAt(4) == c && board.charAt(8) == c) {
            return true;
        } else if (board.charAt(2) == c && board.charAt(4) == c && board.charAt(6) == c) {
            return true;
        }
        return false;
    }
}