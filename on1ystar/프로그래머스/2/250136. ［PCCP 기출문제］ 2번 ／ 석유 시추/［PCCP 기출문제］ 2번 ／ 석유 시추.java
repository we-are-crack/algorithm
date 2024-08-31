import java.util.*;

class Solution {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int oilNumber = -1;
    private static final HashMap<Integer, Integer> oilMap = new HashMap<>();

    public int solution(int[][] land) {
        int maxDrilledOil = 0;

        for (int col = 0; col < land[0].length; col++) {
            HashSet<Integer> oilSet = new HashSet<>();
            for (int row = 0; row < land.length; row++) {
                if (land[row][col] == 1) {
                   drillOil(land, row, col);
                   oilSet.add(land[row][col]);
                } else if (land[row][col] < 0) {
                    oilSet.add(land[row][col]);
                }
            }

            int drilledOil = 0;
            for (int oil : oilSet) {
                drilledOil += oilMap.get(oil);
            }

            maxDrilledOil = Math.max(maxDrilledOil, drilledOil);
        }

        return maxDrilledOil;
    }

    private void drillOil(int[][] land, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        land[row][col] = oilNumber;
        int oilCount = 0;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            oilCount++;

            for (int i = 0; i < 4; i++) {
                int nRow = pos[0] + dx[i];
                int nCol = pos[1] + dy[i];
                if (nRow < 0 || nRow >= land.length || nCol < 0 || nCol >= land[0].length) { continue; }
                if (land[nRow][nCol] == 1) {
                    land[nRow][nCol] = oilNumber;
                    queue.add(new int[]{nRow, nCol});
                }
            }
        }

        oilMap.put(oilNumber, oilCount);
        oilNumber--;
    }
}