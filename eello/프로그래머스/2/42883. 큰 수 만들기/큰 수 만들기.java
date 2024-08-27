class Solution {
    
    public String solution(String number, int k) {
        boolean[] deleted = new boolean[number.length()];
        
        int left = 0, right = 1;
        while (right < number.length() && k > 0) {
            while (
                right < number.length()
                && right - left < k
                && number.charAt(left) >= number.charAt(right)) {
                right++;
            }

            if (right < number.length() && number.charAt(left) < number.charAt(right)) {
                for (int i = left; i < right; i++) {
                    deleted[i] = true;
                }
                
                k -= (right - left);
                left = right;
                right++;
            } else {
                left++;
                right = left + 1;
            }
        }
        
        for (int i = 0; i < k; i++) {
            deleted[number.length() - 1 - i] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            if (deleted[i]) continue;
            sb.append(number.charAt(i));
        }
        
        return sb.toString();
    }
}