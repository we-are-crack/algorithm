import java.util.*;

class Solution {
    
    public String[] solution(String[] record) {
        Map<String, String> member = new HashMap<>();
        List<String[]> log = new ArrayList<>();
        
        for (String rec : record) {
            String[] split = rec.split(" ");
            
            if ("Enter".equals(split[0])) {
                member.put(split[1], split[2]);
                log.add(new String[] {split[0], split[1]});
            } else if ("Leave".equals(split[0])) {
                log.add(new String[] {split[0], split[1]});
            } else {
                member.put(split[1], split[2]);
            }
        }
        
        String[] answer = new String[log.size()];
        int i = 0;
        for (String[] l : log) {
            String nickname = member.get(l[1]);
            if ("Enter".equals(l[0])) answer[i++] = nickname + "님이 들어왔습니다.";
            else answer[i++] = nickname + "님이 나갔습니다.";

        }
        
        return answer;
    }
}