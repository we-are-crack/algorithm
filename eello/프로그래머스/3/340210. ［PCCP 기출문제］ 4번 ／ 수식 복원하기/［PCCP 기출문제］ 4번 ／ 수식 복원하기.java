import java.util.*;

class NumberBaseUtils {
    
    public static int to10Base(int num, int base) {
        int result = 0, i = 0;
        while (num > 0) {            
            result += (int) (Math.pow(base, i++) * (num % 10));
            num /= 10;
        }
        
        return result;
    }
    
    public static int toBase(int num, int base) {
        Deque<Integer> remainders = new ArrayDeque<>();
        int mok = 0;
        while (num >= base) {
            remainders.addLast(num % base);
            num /= base;
        }
        
        String str = "" + num;
        while (!remainders.isEmpty()) {
            str += remainders.pollLast();
        }
        
        return Integer.parseInt(str);
    }
}

class Expression {
    int num1;
    Operation oper;
    int num2;
    int result;
    String strExpression;
    
    public static Expression of(String strExpression) {
        Expression expression = new Expression();
        expression.strExpression = strExpression;
        
        String[] split = strExpression.split(" ");
        expression.num1 = Integer.parseInt(split[0]);
        expression.oper = "+".equals(split[1]) ? Operation.PLUS : Operation.MINUS;
        expression.num2 = Integer.parseInt(split[2]);
        expression.result = "X".equals(split[4]) ? -1 : Integer.parseInt(split[4]);
        
        return expression;
    }
    
    public List<Integer> findBaseNCandidates() {
        // n 진법일 때 각 자리의 숫자는 n 미만이어야 함
        List<Integer> candidates = new ArrayList<>();
        int max = 0;
        char[] charArr = strExpression.toCharArray();
        for (char ch : charArr) {
            if ('0' <= ch && ch <= '9') {
                max = Math.max(max, ch - '0');
            }
        }        

        for (int base = 9; base > max; base--) {
            if (result == -1) {
                candidates.add(base);
            } else {
                if (calculate(base) == result) {
                    candidates.add(base);
                }
            }
        }
        
        return candidates;
    }
    
    /**
        base 진법인 수를 10진법으로 변환해 계산했을 때 결과 계산
     */
    public int calculate(int base) {
        int n1 = NumberBaseUtils.to10Base(num1, base);
        int n2 = NumberBaseUtils.to10Base(num2, base);
        
        return NumberBaseUtils.toBase(oper.calculate(n1, n2), base);
    }
    
    public String toStringWith(int result) {
        return new StringBuilder().append(num1).append(" ")
            .append(oper).append(" ")
            .append(num2).append(" ")
            .append("= ").append(result == -1 ? "?" : result)
            .toString();
    }
    
    static enum Operation {
        PLUS,
        MINUS;
        
        public String toString() {
            return this == PLUS ? "+" : "-";
        }
        
        public int calculate(int num1, int num2) {
            return this == PLUS ? num1 + num2 : num1 - num2;
        }
    }
}

class Solution {
    
    public String[] solution(String[] expressions) {
        Expression[] exprs = new Expression[expressions.length];
        int[] count = new int[10];
        for (int i = 0; i < expressions.length; i++) {
            exprs[i] = Expression.of(expressions[i]);
            
            for (int cand : exprs[i].findBaseNCandidates()) {
                count[cand]++;
            }
        }
        
        List<Integer> candidates = new ArrayList<>();
        for (int base = 2; base < 10; base++) {
            if (count[base] == exprs.length) {
                candidates.add(base);
            }
        }
                
        List<String> answer = new ArrayList<>();
        for (Expression expr : exprs) {
            if (expr.result != -1) {
                continue;
            }
            
            Set<Integer> results = new HashSet<>();
            int result = 0;
            for (Integer base : candidates) {
                result = expr.calculate(base);
                results.add(result);
            }
            answer.add(expr.toStringWith(results.size() == 1 ? result : -1));
        }
        
        return answer.toArray(new String[0]);
    }
}