import java.util.*;

class Solution {
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        File[] f = new File[files.length];
        for (int i = 0; i < files.length; i++) {
            f[i] = new File(files[i]);
        }
        
        Arrays.sort(f);
        for (int i = 0; i < files.length; i++) {
            answer[i] = f[i].getName();
        }
        
        return answer;
    }
    
    private static class File implements Comparable<File> {
        private String name;
        private String head;
        private int number;
        
        public File(String file) {
            name = file;
            
            int i = 0;
            char[] chs = file.toCharArray();

            StringBuilder sb = new StringBuilder();
            while (!NumberUtils.isNumber(chs[i])) {
                sb.append(chs[i++]);
            }
            head = sb.toString();

            sb.setLength(0);
            
            while (i < file.length() && sb.length() < 5 && NumberUtils.isNumber(chs[i])) {
                sb.append(chs[i++]);
            }
            number = Integer.parseInt(sb.toString());
        }
        
        public String getName() {
            return name;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("fullname=").append(name);
            sb.append(", head=").append(head);
            sb.append(", number=").append(number);
            return sb.toString();
        }
        
        @Override
        public int compareTo(File o) {
            if (!head.equalsIgnoreCase(o.head)) {
                return head.compareToIgnoreCase(o.head);
            }
            
            return number - o.number;
        }
    }
    
    private static class NumberUtils {
        public static boolean isNumber(char ch) {
            return '0' <= ch & ch <= '9';
        }
    }
}