import java.util.*;

class Solution {
    
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, (a, b) -> (a.length() - b.length()));
        Set<String> prefix = new TreeSet<>();
        
        for (String phone : phone_book) {
            for (int i = 1; i <= phone.length(); i++) {
                if (prefix.contains(phone.substring(0, i))) {
                    return false;
                }
            }
            
            prefix.add(phone);
        }
        
        return true;
    }
}