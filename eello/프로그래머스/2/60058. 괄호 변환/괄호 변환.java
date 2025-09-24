class Solution {
    
    private static final char OPEN = '(', CLOSE = ')';
    
    public String solution(String p) {
        // 1. 입력 문자가 빈 문자열인 경우, 빈 문자열 반환
        if (p.isBlank()) {
            return p;
        }
        
        // 문자열을 u, v로 분리
        // u = 균형잡힌 괄호 문자열로 가능한 최소 길이의 문자열
        // v = u를 제외한 나머지 문자열
        
        // count = open <-> close 괄호의 개수 차이, if(open) +1 else -1
        int count = p.charAt(0) == OPEN ? 1 : -1;
        boolean isValid = count >= 0;
        int i = 0;
        while (++i < p.length()) {
            count += p.charAt(i) == OPEN ? 1 : -1;
            
            if (count == 0) {
                // count == 0 이 되는 순간 서브문자열의 범위 0..i는 균형잡힌 괄호 문자열
                break;
            } else if (count < 0) {
                // count < 0 이 되는 순간이 있는 경우는 
                // CLOSE가 OPEN 보다 많아지는 순간이 있으므로 올바르지 않은 괄호 문자열
                isValid = false;
            }
        }
        
        String u = p.substring(0, i + 1);
        String v = i == p.length() - 1 ? "" : p.substring(i + 1);
        
        String answer = "";
        if (isValid) {
            // u가 올바른 괄호 문자열인 경우
            answer = u + solution(v);
        } else {
            // u가 균형잡힌 괄호 문자열인 경우
            // ( + v에 대해 재귀적으로 수행한 결과 + )
            answer = "(" + solution(v) + ")";
            
            // u의 첫 번째와 마지막 문자를 제거한 문자열
            u = u.substring(1, u.length() - 1);
            for (char ch : u.toCharArray()) {
                // 첫 번째와 마지막 문자를 제거한 u의 문자열에 대해 방향을 뒤집어 뒤에 더함
                if (ch == OPEN) {
                    answer += CLOSE;
                } else answer += OPEN;
            }
        }
        
        return answer;
    }
}