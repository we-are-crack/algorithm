class Solution {
    
    public int solution(String s) {
        int answer = s.length();
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            answer = Math.min(answer, compression(s, unit));
        }
        
        return answer;
    }
    
    private int compression(String str, int u) {
        int result = u;
        String unit = str.substring(0, u);
        int same = 1;
        for (int i = u; i < str.length(); i += u) {
            if (str.length() < i + u) {
                break;
            }
            
            if (isEqual(unit, str, i)) {
                same++;
            } else {
                result += same == 1 ? 0 : String.valueOf(same).length();
                result += u;
                
                unit = str.substring(i, i + u);
                same = 1;
            }
        }
        
        result += same == 1 ? 0 : String.valueOf(same).length();
        result += str.length() % u;        
        return result;
    }
    
    private boolean isEqual(String unit, String str, int s) {
        for (int i = 0; i < unit.length(); i++) {
            if (unit.charAt(i) != str.charAt(s + i)) {
                return false;
            }
        }
        return true;
    }
}