class Solution {
    
    public int solution(int n) {
        int nBitCount = Integer.bitCount(n);
        while (nBitCount != Integer.bitCount(++n));
        return n;
    }
}