class Solution {
    public int solution(String s) {
        int answer = s.length();
        StringBuilder sb = new StringBuilder(s);
        StringBuilder compString = new StringBuilder();
        String prev = "";
        String now = "";
        int compCount;
        for(int compUnitLength = 1; compUnitLength <= s.length() / 2; compUnitLength++) {
            compCount = 1;
            for(int i = 0; i < s.length(); i += compUnitLength) {
                now = sb.substring(i, Math.min(i + compUnitLength, sb.length()));
                if(prev.isEmpty()) prev = now;
                else {
                    if(now.equals(prev)) compCount++;
                    else {
                        compString.append(compCount == 1 ? prev : compCount + prev);
                        compCount = 1;
                        prev = now;
                    }
                }
            }
            if(compCount != 1) compString.append(compCount).append(now);
            else compString.append(now);
            answer = Math.min(answer, compString.length());
            prev = "";
            compString.delete(0, compString.length());
        }
        return answer;
    }
}