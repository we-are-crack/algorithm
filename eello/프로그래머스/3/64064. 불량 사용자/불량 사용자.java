import java.util.*;

class Solution {
    
    private String[] userId;
    private String[] bannedId;
    private Set<String> bannedSet;
    
    
    public int solution(String[] user_id, String[] banned_id) {
        this.userId = user_id;
        this.bannedId = banned_id;
        this.bannedSet = new HashSet<>();
        
        return ban(0, new String[bannedId.length], new boolean[userId.length]);
    }
    
    private int ban(int idx, String[] banList, boolean[] banned) {
        if (idx == banList.length) {
            String[] sortedBanList = Arrays.copyOf(banList, banList.length);
            Arrays.sort(sortedBanList);
            
            StringBuilder sb = new StringBuilder();
            for (String sbl : sortedBanList) {
                sb.append(sbl);
            }
            
            if (bannedSet.contains(sb.toString())) {
                return 0;
            } else {
                bannedSet.add(sb.toString());
                return 1;   
            }
        }
        
        int ret = 0;
        for (int i = 0; i < userId.length; i++) {
            if (isCandidate(userId[i], bannedId[idx]) && !banned[i]) {
                banList[idx] = userId[i];
                banned[i] = true;
                ret += ban(idx + 1, banList, banned);
                banned[i] = false;
            }
        }
        
        return ret;
    }
    
    private boolean isCandidate(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }
        
        int len = userId.length();
        for (int i = 0; i < len; i++) {
            if (bannedId.charAt(i) == '*') {
                continue;
            }
            
            if (userId.charAt(i) != bannedId.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}