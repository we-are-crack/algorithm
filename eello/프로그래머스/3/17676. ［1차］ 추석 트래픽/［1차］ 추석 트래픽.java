import java.util.*;

class Solution {
    
    public int solution(String[] lines) {
        int answer = 0;
        
        Request[] requests = new Request[lines.length];
        for (int i = 0; i < lines.length; i++) {
            requests[i] = new Request(lines[i]);
        }
        
        for (int i = 0; i < requests.length; i++) {
            int end = requests[i].getStart();
            int start = end - 999;
            int count = 1;
            
            for (int j = 0; j < requests.length; j++) {
                if (i == j) {
                    continue;
                }
                
                Request request = requests[j];
                if (request.getEnd() < start || end < request.getStart()) {
                    continue;
                }
                
                count++;
            }
            
            answer = Math.max(answer, count);
        }
        
        return answer;
    }
    
    private static class Request {
        private int start;
        private int end;
        private int processingTime;
        
        public Request(String log) {
            String[] elem = log.split(" ");
            String[] time = elem[1].split(":");
            String[] second = time[2].split(".");
            
            end += Integer.parseInt(time[0]) * 3_600_000 + 
                Integer.parseInt(time[1]) * 60_000 + 
                (int) (Double.parseDouble(time[2]) * 1_000);
            
            String pt = elem[2].substring(0, elem[2].length() - 1);
            processingTime = (int) (Double.parseDouble(pt) * 1_000);
            
            start = end - processingTime + 1;
        }
        
        public int getStart() {
            return start;
        }
        
        public int getEnd() {
            return end;
        }
    }
}