class Solution {
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = toBinaryString(numbers[i]);
            answer[i] = isValid(binary.toCharArray(), 0, binary.length() - 1, true) ? 1 : 0;
        }
        
        return answer;
    }
    
    private boolean isValid(char[] binary, int s, int e, boolean parentExists) {
        if (!parentExists) {
            for (int i = s; i <= e; i++) {
                if (binary[i] == '1') {
                    return false;
                }
            }
            return true;
        }
        
        if (s == e) {
            return true;
        }
        
        int mid = (s + e) / 2;
        boolean parent = binary[mid] == '1';
        
        boolean validate = true;
        validate &= isValid(binary, s, mid - 1, parent);
        validate &= isValid(binary, mid + 1, e, parent);
        return validate;
    }
    
    private String toBinaryString(long number) {
        String binary = Long.toBinaryString(number);
        int temp = 1;
        while (temp - 1 < binary.length()) {
            temp *= 2;
        }
        
        return "0".repeat(temp - 1 - binary.length()) + binary;
    }
}