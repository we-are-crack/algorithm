import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};
        Map<String, Integer> dataColumn = new HashMap<>();
        String[] col = {"code", "date", "maximum", "remain"};
        int i = 0;
        for(String str : col) {
            dataColumn.put(str, i);
            i++;
        }
        List<int[]> selectedData = new ArrayList<>();
        int j = 0;
        for(int[] row : data) {
            if(row[dataColumn.get(ext)] < val_ext) {
                selectedData.add(row);
            }
        }
        selectedData.sort(Comparator.comparingInt(row -> row[dataColumn.get(sort_by)]));
        return selectedData.toArray(new int[0][]);
    }
}