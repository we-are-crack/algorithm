class Solution {
    
    public int solution(int k, int[][] dungeons) {
        int answer = rec(dungeons, k, 0);
        return answer;
    }
    
    private int rec(int[][] dungeons, int k, int bitmask) {
        int ret = Integer.bitCount(bitmask);
        for (int i = 0; i < dungeons.length; i++) {
            if ((bitmask & (1 << i)) == 0 && dungeons[i][0] <= k) {
                ret = Math.max(ret, rec(dungeons, k - dungeons[i][1], bitmask | (1 << i)));
            }
        }
        
        return ret;
    }
}