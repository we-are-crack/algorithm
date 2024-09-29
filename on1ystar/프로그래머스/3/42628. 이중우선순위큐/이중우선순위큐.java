import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        return test(operations);
    }
    
    private int[] test(String[] operations) {
        DualPriorityQueue dpq = new DualPriorityQueue();

        for (int i = 0; i < operations.length; i++) {
            String[] command = operations[i].split(" ");
            operate(dpq, command[0].charAt(0), Integer.parseInt(command[1]));
        }

        if (dpq.peekMax() == null || dpq.peekMin() == null) {
            return new int[] {0, 0};
        } else {
            return new int[] {dpq.peekMax(), dpq.peekMin()};
        }
    }

    private void operate(DualPriorityQueue dpq, char op, int number) {
        if (op == 'I') {
            dpq.insert(number);
        } else {
            if (number == 1) {
                dpq.removeMax();
            } else {
                dpq.removeMin();
            }
        }
    }

    static class DualPriorityQueue {
        private final PriorityQueue<Integer> pqMax = new PriorityQueue<>(Comparator.reverseOrder());
        private final PriorityQueue<Integer> pqMin = new PriorityQueue<>();
        private final PriorityQueue<Integer> deletedMax = new PriorityQueue<>(Comparator.reverseOrder());
        private final PriorityQueue<Integer> deletedMin = new PriorityQueue<>();

        public Integer peekMax() {
            while (!pqMax.isEmpty() && !deletedMax.isEmpty() && Objects.equals(pqMax.peek(), deletedMax.peek())) {
                pqMax.remove();
                deletedMax.remove();
            }

            return pqMax.peek();
        }

        public Integer peekMin() {
            while (!pqMin.isEmpty() && !deletedMin.isEmpty() && Objects.equals(pqMin.peek(), deletedMin.peek())) {
                pqMin.remove();
                deletedMin.remove();
            }

            return pqMin.peek();
        }

        public void insert(int number) {
            pqMax.add(number);
            pqMin.add(number);
        }

        public void removeMax() {
            if (peekMax() != null) {
                deletedMin.add(pqMax.remove());
            }
        }

        public void removeMin() {
            if (peekMin() != null) {
                deletedMax.add(pqMin.remove());
            }
        }
    }
}