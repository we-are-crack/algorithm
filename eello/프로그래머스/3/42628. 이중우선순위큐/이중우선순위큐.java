import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        
        int insertCount = 0;
        for (String oper : operations) {
            StringTokenizer st = new StringTokenizer(oper);
            String type = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            
            if (type.equals("I")) {
                minHeap.add(number);
                maxHeap.add(number);
                insertCount++;
            } else {
                if (maxHeap.size() + minHeap.size() == insertCount) {
                    continue;
                }
                
                if (number > 0) {
                    maxHeap.poll();
                } else {
                    minHeap.poll();
                }
                
                if (maxHeap.size() + minHeap.size() == insertCount) {
                    maxHeap.clear();
                    minHeap.clear();
                    insertCount = 0;
                }
            }
        }
                
        return maxHeap.size() + minHeap.size() == insertCount ? new int[] {0, 0} : new int[] {maxHeap.peek(), minHeap.peek()};
    }
}