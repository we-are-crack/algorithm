import java.util.*;

class Solution {
    
    public int[] solution(String[] operations) {
        DualPriorityQueue<Integer> dpq = new DualPriorityQueue<>();
        
        for (String oper : operations) {
            StringTokenizer st = new StringTokenizer(oper);
            if ("I".equals(st.nextToken())) {
                dpq.offer(Integer.parseInt(st.nextToken()));
            } else {
                if (Integer.parseInt(st.nextToken()) == 1) {
                   dpq.pollLast(); 
                } else dpq.pollFirst();
            }
        }
        
        return dpq.isEmpty() ? new int[] {0, 0} : new int[] {dpq.peekLast(), dpq.peekFirst()};
    }
    
    private static class DualPriorityQueue<T extends Comparable<? super T>> {
		private int size;
		private PriorityQueue<T> minHeap;
		private PriorityQueue<T> maxHeap;
		private Stack<T> maxDelStack = new Stack();
		private Stack<T> minDelStack = new Stack();

		public DualPriorityQueue() {
			minHeap = new PriorityQueue<>();
			maxHeap = new PriorityQueue<>((a, b) -> -1 * a.compareTo(b));
		}

		public void offer(T value) {
            sync();
            
			size++;
			minHeap.offer(value);
			maxHeap.offer(value);
		}

		public T pollFirst() {
			if (isEmpty()) {
				return null;
			}

			size--;
			T poll = minHeap.poll();
			minDelStack.push(poll);
            
            sync();
            
			return poll;
		}

		public T pollLast() {
			if (isEmpty()) {
				return null;
			}
            
			size--;
			T poll = maxHeap.poll();
			maxDelStack.push(poll);
            
            sync();
            
			return poll;
		}

		public boolean isEmpty() {
			return size == 0;
		}
        
        public T peekFirst() {
            return minHeap.peek();
        }
        
        public T peekLast() {
            return maxHeap.peek();
        }
        
        private void sync() {
            while (!maxDelStack.isEmpty() && minHeap.peek() == maxDelStack.peek()) {
                minHeap.poll();
				maxDelStack.pop();
			}
            
            while (!minDelStack.isEmpty() && maxHeap.peek() == minDelStack.peek()) {
                maxHeap.poll();
				minDelStack.pop();
			}
        }
	}
}