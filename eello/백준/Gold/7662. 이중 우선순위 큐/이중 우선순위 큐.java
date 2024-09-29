import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		while (t-- > 0) {
			DualPriorityQueue<Integer> dpq = new DualPriorityQueue<>();
			int k = Integer.parseInt(br.readLine());
			while (k-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				if ("I".equals(st.nextToken())) {
					dpq.offer(Integer.parseInt(st.nextToken()));
				} else {
					if (Integer.parseInt(st.nextToken()) == 1) {
						dpq.pollLast();
					} else dpq.pollFirst();
				}
			}

			answer.append(dpq.isEmpty() ? "EMPTY" : dpq.peekLast() + " " + dpq.peekFirst());
			answer.append("\n");
		}

		System.out.println(answer);
	}

	private static class DualPriorityQueue<T extends Comparable<? super T>> {
		private int size;
		private PriorityQueue<T> minHeap;
		private PriorityQueue<T> maxHeap;
		private Map<T, Integer> minDel;
		private Map<T, Integer> maxDel;

		public DualPriorityQueue() {
			minHeap = new PriorityQueue<>();
			maxHeap = new PriorityQueue<>((a, b) -> -1 * a.compareTo(b));
			minDel = new HashMap<>();
			maxDel = new HashMap<>();
		}

		public void offer(T value) {
			size++;
			minHeap.offer(value);
			maxHeap.offer(value);
		}

		public T pollFirst() {
			if (isEmpty()) return null;

			sync(minHeap, maxDel);

			size--;
			T remove = minHeap.poll();
			minDel.put(remove, minDel.getOrDefault(remove, 0) + 1);
			return remove;
		}

		public T pollLast() {
			if (isEmpty()) return null;

			sync(maxHeap, minDel);

			size--;
			T remove = maxHeap.poll();
			maxDel.put(remove, maxDel.getOrDefault(remove, 0) + 1);
			return remove;
		}

		public T peekFirst() {
			if (isEmpty()) return null;
			sync(minHeap, maxDel);
			return minHeap.peek();
		}

		public T peekLast() {
			if (isEmpty()) return null;
			sync(maxHeap, minDel);
			return maxHeap.peek();
		}

		public boolean isEmpty() {
			return size == 0;
		}

		private void sync(PriorityQueue<T> heap, Map<T, Integer> del) {
			while (del.containsKey(heap.peek())) {
				T remove = heap.poll();

				Integer count = del.get(remove);
				if (count == 1) {
					del.remove(remove);
				} else del.put(remove, count - 1);
			}
		}
	}
}
