class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int num = 1;
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = num++;
            }
        }
        
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int r1 = queries[i][0] - 1, c1 = queries[i][1] - 1;
            int r2 = queries[i][2] - 1, c2 = queries[i][3] - 1;
            answer[i] = rotate(matrix, r1, c1, r2, c2);
        }
        
        return answer;
    }
    
    private int rotate(int[][] matrix, int r1, int c1, int r2, int c2) {
        int min = Integer.MAX_VALUE;
        int temp = matrix[r1][c1];
        
        // 왼쪽 열 아래 -> 위
        for (int r = r1; r < r2; r++) {
            matrix[r][c1] = matrix[r + 1][c1];
            min = Math.min(min, matrix[r][c1]);
        }
        
        // 아래 행 오른쪽 -> 왼쪽
        for (int c = c1; c < c2; c++) {
            matrix[r2][c] = matrix[r2][c + 1];
            min = Math.min(min, matrix[r2][c]);
        }
        
        // 오른쪽 열 위 -> 아래
        for (int r = r2; r > r1; r--) {
            matrix[r][c2] = matrix[r - 1][c2];
            min = Math.min(min, matrix[r][c2]);
        }
        
        // 위 행 왼쪽 -> 오른쪽
        for (int c = c2; c > c1; c--) {
            matrix[r1][c] = matrix[r1][c - 1];
            min = Math.min(min, matrix[r1][c]);
        }
        
        matrix[r1][c1 + 1] = temp;
        return Math.min(min, temp);
    }
}