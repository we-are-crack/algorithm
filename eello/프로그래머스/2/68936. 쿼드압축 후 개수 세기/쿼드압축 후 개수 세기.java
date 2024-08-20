class Solution {
    
    public int[] solution(int[][] arr) {
        int size = arr.length;
        int[] answer = new int[2];
        compress(arr, 0, 0, size, answer);
        return answer;
    }
    
    private void compress(int[][] arr, int luy, int lux, int size, int[] count) {
        if (size == 1 || isAllSame(arr, luy, lux, size)) {
            count[arr[luy][lux]]++;
            return;
        }
        
        size /= 2;
        compress(arr, luy, lux + size, size, count); // 1사분면
        compress(arr, luy + size, lux + size, size, count); // 2사분면
        compress(arr, luy + size, lux, size, count); // 3사분면
        compress(arr, luy, lux, size, count); // 4사분면
    }
    
    private boolean isAllSame(int[][] arr, int luy, int lux, int size) {
        for (int y = luy; y < luy + size; y++) {
            for (int x = lux; x < lux + size; x++) {
                if (arr[luy][lux] != arr[y][x]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}