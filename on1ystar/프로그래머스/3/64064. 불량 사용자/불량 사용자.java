import java.util.*;

class Solution {
    private final HashSet<ArrayList<String>> bannedIds = new HashSet<>();

    public int solution(String[] userId, String[] bannedId) {
        findBanId(userId, new boolean[userId.length], bannedId, 0, new ArrayList<>());
        return bannedIds.size();
    }
    private void findBanId(String[] userId, boolean[] foundUserId,
                           String[] bannedId, int banIndex, ArrayList<String> foundIds) {
        if(banIndex == bannedId.length) {
            ArrayList<String> foundId = new ArrayList<>(foundIds);
            foundId.sort(String::compareTo);
            bannedIds.add(foundId);
        }
        else{
            for(int i = 0; i < userId.length; i++){
                if(!foundUserId[i]){
                    if(isMatching(userId[i], bannedId[banIndex])) {
                        foundUserId[i] = true;
                        foundIds.add(userId[i]);
                        findBanId(userId, foundUserId, bannedId, banIndex+1, foundIds);
                        foundUserId[i] = false;
                        foundIds.remove(foundIds.indexOf(userId[i]));
                    }
                }
            }
        }
    }
    private boolean isMatching(String userId, String bannedId) {
        if(userId.length() != bannedId.length()) return false;
        for(int i = 0; i < userId.length(); i++) {
            if(bannedId.charAt(i) == '*') continue;
            if(userId.charAt(i) != bannedId.charAt(i)) return false;
        }
        return true;
    }
}