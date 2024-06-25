class Solution {
    static final String[] operatorPrecedences = new String[]{"+-*", "+*-", "-+*", "-*+", "*+-", "*-+",};
    public long solution(String expression) {
        long answer = 0;
        String[] operators = expression.split("\\d+");  // 첫 번째 요소는 제외해야 함
        String[] numbers = expression.split("\\D+");
        for(String operatorPrecedence : operatorPrecedences) {
            String[] tempNumbers = numbers.clone();
            for(char operator : operatorPrecedence.toCharArray()) {
                for(int i = 1; i < numbers.length; i++) {
                    if(operators[i].toCharArray()[0] == operator) {
                        int n1Index = i - 1;
                        while(tempNumbers[n1Index].isEmpty()) n1Index--;
                        tempNumbers[n1Index] = String.valueOf(calculate(Long.parseLong(tempNumbers[n1Index]), Long.parseLong(tempNumbers[i]), operator));
                        tempNumbers[i] = "";
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(tempNumbers[0])));
        }
        return answer;
    }
    private long calculate(long n1, long n2, char op){
        return switch (op) {
            case '+' -> n1 + n2;
            case '-' -> n1 - n2;
            case '*' -> n1 * n2;
            default -> 0;
        };
    }
}