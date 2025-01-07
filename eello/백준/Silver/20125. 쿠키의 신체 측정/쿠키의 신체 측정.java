import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] square = new char[n][n];
        for (int i = 0; i < n; i++) {
            square[i] = br.readLine().toCharArray();
        }

        int[] heartPosition = findHeartPositions(square);
        int[] body = new int[5]; // leftArm, rightArm, waist, leftLeg, rightLeg

        int[][] dir = {{0, -1}, {1, 0}, {0, 1}}; // left, down, right
        body[0] = estimateLength(square, new int[]{heartPosition[0], heartPosition[1] - 1}, dir[0]);
        body[1] = estimateLength(square, new int[]{heartPosition[0], heartPosition[1] + 1}, dir[2]);
        body[2] = estimateLength(square, new int[]{heartPosition[0] + 1, heartPosition[1]}, dir[1]);
        body[3] = estimateLength(square, new int[]{heartPosition[0] + body[2] + 1, heartPosition[1] - 1}, dir[1]);
        body[4] = estimateLength(square, new int[]{heartPosition[0] + body[2] + 1, heartPosition[1] + 1}, dir[1]);

        StringBuilder answer = new StringBuilder();
        answer.append(heartPosition[0] + 1).append(" ").append(heartPosition[1] + 1).append("\n");
        for (int i = 0; i < body.length; i++) {
            answer.append(body[i]).append(" ");
        }

        System.out.println(answer);
    }

    private static int[] findHeartPositions(char[][] square) {
        int[] heartPosition = new int[2];

        loop1: for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                if (square[i][j] == '*') {
                    heartPosition[0] = i + 1;
                    heartPosition[1] = j;
                    break loop1;
                }
            }
        }

        return heartPosition;
    }

    private static int estimateLength(char[][] square, int[] p, int[] d) {
        int len = 0;
        while (check(square, p) && square[p[0]][p[1]] == '*') {
            len++;
            p[0] += d[0];
            p[1] += d[1];
        }

        return len;
    }

    private static boolean check(char[][] square, int[] p) {
        int n = square.length;
        return 0 <= p[0] && p[0] < n && 0 <= p[1] && p[1] < n;
    }
}
