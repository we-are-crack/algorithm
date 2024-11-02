import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static class Matrix {
        private final int row;
        private final int col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        Matrix[] matrices = new Matrix[tc];

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrices[i] = new Matrix(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        if (tc == 1) {
            System.out.println(0);
            return;
        }

        int[][] d = new int[tc][tc];
        for (int i = 1; i < tc; i++) {
            for (int j = i; j < tc; j++) {

            int minCount = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    minCount = Math.min(minCount, d[k][j - i + k] 
                            + d[i - k - 1][j] 
                            + matrices[j - i].getRow() * matrices[j - i + k].getCol() * matrices[j].getCol());
                }

                d[i][j] = minCount;
            }
        }

        System.out.println(d[tc - 1][tc - 1]);
    }
}