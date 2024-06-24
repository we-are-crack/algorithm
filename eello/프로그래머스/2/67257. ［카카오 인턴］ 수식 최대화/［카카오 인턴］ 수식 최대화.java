import java.util.*;

class Solution {
    
    private static final int NUMBER_OF_OPERATOR = 3;
    
    private List<int[]> priorities = new ArrayList<>();
    
    public long solution(String expression) {
        Map<Character, Integer> ortPriority = new HashMap<>();
        makePriority(0, new int[NUMBER_OF_OPERATOR], new boolean[NUMBER_OF_OPERATOR]);
        
        long answer = 0;
        for (int[] p : priorities) {
            ortPriority.put('*', p[0]);
            ortPriority.put('+', p[1]);
            ortPriority.put('-', p[2]);
            
            answer = Math.max(answer, calculateAll(ortPriority, expression));
        }
        
        return answer;
    }
    
    private void makePriority(int idx, int[] priority, boolean[] visited) {
        if (idx == NUMBER_OF_OPERATOR) {
            priorities.add(Arrays.copyOf(priority, NUMBER_OF_OPERATOR));
        }
        
        for (int i = 0; i < NUMBER_OF_OPERATOR; i++) {
            if (!visited[i]) {
                priority[idx] = i;
                visited[i] = true;
                makePriority(idx + 1, priority, visited);
                visited[i] = false;
            }
        }
    }
    
    private long calculateAll(Map<Character, Integer> ortPriority, String expression) {
        Stack<Long> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();
        
        StringBuilder sb = new StringBuilder();
        for (char ex : expression.toCharArray()) {
            if (isOperator(ex)) {
                operand.push(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
                
                while (!operator.isEmpty() && ortPriority.get(operator.peek()) >= ortPriority.get(ex)) {
                    long ord2 = operand.pop();
                    long ord1 = operand.pop();
                    char ort = operator.pop();
                    
                    operand.push(calculate(ord1, ord2, ort));
                }
                
                operator.push(ex);
            } else {
                sb.append(ex);
            }
        }
        operand.push(Long.parseLong(sb.toString()));
        
        
        while (operand.size() != 1) {
            long ord2 = operand.pop();
            long ord1 = operand.pop();
            char ort = operator.pop();

            operand.push(calculate(ord1, ord2, ort));
        }
        
        return Math.abs(operand.pop());
    }
    
    private boolean isOperator(char ch) {
        return ch == '*' || ch == '+' || ch == '-';
    }
    
    private long calculate(long ord1, long ord2, char ort) {
        switch(ort) {
            case '*' :
                return ord1 * ord2;
            case '+' :
                return ord1 + ord2;
            default :
                return ord1 - ord2;    
        }
    }
}