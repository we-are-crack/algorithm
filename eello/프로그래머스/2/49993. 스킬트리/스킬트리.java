import java.util.regex.*;

class Solution {
    
    /*
     *  정규표현식을 사용한 String.startsWith 풀이
     */
    public int solution(String skill, String[] skill_trees) {
        Pattern pattern = Pattern.compile("[^" + skill + "]");
        
        int answer = 0;
        for (String st : skill_trees) {
            if (skill.startsWith(pattern.matcher(st).replaceAll(""))) {
                answer++;
            }
        }
        
        return answer;
    }
    
    /*
     *  정규표현식을 사용하지 않은 String.startsWith 풀이
     
    public int solution(String skill, String[] skill_trees) {
        boolean[] necessary = new boolean[26];
        for (char s : skill.toCharArray()) {
            necessary[s - 'A'] = true;
        }
        
        int answer = 0;
        for (String st : skill_trees) {
            StringBuilder sb = new StringBuilder();
            for (char s : st.toCharArray()) {
                if (necessary[s - 'A']) {
                    sb.append(s);
                }
            }
            
            if (skill.startsWith(sb.toString())) {
                answer++;
            }
        }
        
        return answer;
    }
    
    */
}