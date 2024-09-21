class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video = convert(video_len);
        int current = convert(pos);
        int opStart = convert(op_start);
        int opEnd = convert(op_end);
        
        if (opStart <= current && current < opEnd) {
            current = opEnd;
        }
        
        for (String cmd : commands) {
            if ("prev".equals(cmd)) {
                current = Math.max(0, current - 10);
            } else {
                current = Math.min(video, current + 10);
            }
            
            if (opStart <= current && current < opEnd) {
                current = opEnd;
            }
        }
        
        return convert(current);
    }
    
    private int convert(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
    
    private String convert(int second) {
        int m = second / 60;
        int s = second % 60;
        return String.format("%02d:%02d", m, s);
    }
}