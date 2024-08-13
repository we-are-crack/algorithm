class Solution {
    
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
}