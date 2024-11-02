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

        public int getMultiCount(Matrix matrix) {
            return this.row * this.col * matrix.getCol();
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

        // d[곱한 행렬 개수 + 1][마지막 행렬 위치]
        // dxy = matrices[y - x] * ... * matrices[y - 1] * matrices[y - 0]
        int[][] d = new int[tc][tc];
        for (int i = 1; i < tc; i++) {
            for (int j = i; j < tc; j++) {

            int minCount = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    Matrix leftMatrix = new Matrix(matrices[j - i].getRow(), matrices[j - i + k].getCol());
                    Matrix rightMatrix = new Matrix(matrices[j - i + k + 1].getRow(), matrices[j].getCol());
                    int leftMultiCount = d[k][j - i + k];
                    int rightMultiCount = d[i - k - 1][j];
                    minCount = Math.min(minCount, leftMultiCount + rightMultiCount + leftMatrix.getMultiCount(rightMatrix));
                }

                d[i][j] = minCount;
            }
        }

        System.out.println(d[tc - 1][tc - 1]);
    }
}