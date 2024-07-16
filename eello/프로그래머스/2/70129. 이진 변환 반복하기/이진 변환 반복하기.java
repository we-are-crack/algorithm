class Solution {
    
    public int[] solution(String s) {        
        int conversion = 0, remove = 0;
        while (!"1".equals(s)) {
            conversion++;
            
            int zeroCount = getZeroCount(s);
            remove += zeroCount;
            
            s = Integer.toBinaryString(s.length() - zeroCount);
        }
        
        return new int[] {conversion, remove};
    }
    
    private int getZeroCount(String s) {
        int zeroCount = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '0') {
                zeroCount++;
            }
        }
        
        return zeroCount;
    }
}