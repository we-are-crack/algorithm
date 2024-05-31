import java.util.*;

class Solution {
    
    private Set<Integer> bannedSet;
    
    public int solution(String[] user_id, String[] banned_id) {
        bannedSet = new HashSet<>();
        ban(user_id, banned_id, 0, 0);
        return bannedSet.size();
    }
    
    private void ban(String[] userId, String[] bannedId, int idx, int bitmask) {
        if (bannedId.length == idx) {
            bannedSet.add(bitmask);
            return;
        }
        
        for (int i = 0; i < userId.length; i++) {
            if ((bitmask & 1 << (i + 1)) == 0 && isCandidate(userId[i], bannedId[idx])) {
                ban(userId, bannedId, idx + 1, bitmask | 1 << (i + 1));
            }
        }
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