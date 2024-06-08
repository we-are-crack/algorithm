class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int i = 0;
        for(String[] place : places) {
            boolean isKeepDistsnce = true;
            for(int row = 0; row < place.length; row++) {
                for(int col = 0; col < place[row].length(); col++) {
                    if(place[row].charAt(col) != 'P') continue;
                    if(!distancingCheck(place, row, col, row, col - 1, false)){
                        isKeepDistsnce = false;
                        break;
                    }
                }
                if(!isKeepDistsnce) break;
            }
            if(isKeepDistsnce) answer[i++] = 1;
            else answer[i++] = 0;
        }
        return answer;
    }

    boolean distancingCheck(String[] place, int row, int col, int preRow, int preCol, boolean isRecheck) {
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        for(int i = 0; i < 4; i++) {
            int tmpRow = row + dRow[i];
            int tmpCol = col + dCol[i];
            if(tmpRow == preRow && tmpCol == preCol) continue;
            if(tmpRow < 0 || tmpRow >= place.length || tmpCol < 0 || tmpCol >= place[tmpRow].length()) continue;
            if(place[tmpRow].charAt(tmpCol) == 'P') return false;
            else if(place[tmpRow].charAt(tmpCol) == 'O') {
                if(isRecheck) continue;
                if(!distancingCheck(place, tmpRow, tmpCol, row, col, true)) return false;
            }
        }
        return true;
    }
}