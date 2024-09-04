class Solution {
    
    public int solution(String[][] book_time) {
        int[] book = new int[3610];
        for (String[] bt : book_time) {
            int start = convertToMinute(bt[0]);
            int end = convertToMinute(bt[1]) + 10; // 청소시간 포함
            
            book[start] += 1;
            book[end] -= 1;
        }
        
        int answer = 0;
        for (int i = 1; i < book.length; i++) {
            book[i] += book[i - 1];
            answer = Math.max(answer, book[i]);
        }
                
        return answer;
    }
    
    private int convertToMinute(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}