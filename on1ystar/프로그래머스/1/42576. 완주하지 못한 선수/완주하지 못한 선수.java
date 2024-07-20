import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < completion.length; i++) {
            map.put(participant[i], map.getOrDefault(participant[i], 0) + 1);
            map.put(completion[i], map.getOrDefault(completion[i], 0) - 1);
        }
        map.put(participant[participant.length - 1], map.getOrDefault(participant[participant.length - 1], 0) + 1);
        for(int i = 0; i < participant.length; i++) {
            if(map.get(participant[i]) > 0) return participant[i];
        }
        return "";
    }
}