import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static String[][] matrix;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        k = Integer.parseInt(line[2]);
        matrix = new String[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++)
            matrix[i] = br.readLine().split(" ");

        ArrayList<int[]> selectedIndex = new ArrayList<>();
        System.out.println(dfs(selectedIndex, 0, Integer.MIN_VALUE));
    }
    private static int dfs(ArrayList<int[]> selectedIndex, int sum, int max) {
        if(selectedIndex.size() == k) return Math.max(sum, max);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && checkNear(selectedIndex, i, j)) {
                    visited[i][j] = true;
                    selectedIndex.add(new int[]{i, j});
                    max = Math.max(max, dfs(selectedIndex, sum + Integer.parseInt(matrix[i][j]), max));
                    visited[i][j] = false;
                    selectedIndex.remove(selectedIndex.size() - 1);
                }
            }
        }
        return max;
    }
    private static boolean checkNear(ArrayList<int[]> selectedIndex, int i, int j) {
        for(int[] index : selectedIndex) {
            if(index[0] + 1 == i && index[1] == j
                || index[0] - 1 == i && index[1] == j
                || index[0] == i && index[1] + 1 == j
                || index[0] == i && index[1] - 1 == j) {
                return false;
            }
        }
        return true;
    }
 }