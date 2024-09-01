import java.util.List;
import java.util.ArrayList;

class Solution {

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Node[] nodes = new Node[1_000_001];

        for (int[] edge : edges) {
            if (nodes[edge[0]] == null) {
                nodes[edge[0]] = new Node(edge[0], new ArrayList<>(), new ArrayList<>());
            }

            if (nodes[edge[1]] == null) {
                nodes[edge[1]] = new Node(edge[1], new ArrayList<>(), new ArrayList<>());
            }

            nodes[edge[0]].addOut(nodes[edge[1]]);
            nodes[edge[1]].addIn(nodes[edge[0]]);
        }

        int newNodeNumber = findNewNode(nodes);
        answer[0] = newNodeNumber;

        for (Node node : nodes[newNodeNumber].getOut()) {
            int startNodeNumber = node.getNumber();
            int nowNodeNumber = node.getNumber();;
            while (true) {
                List<Node> nowNodeOut = nodes[nowNodeNumber].getOut();

                if (nowNodeOut.size() > 1) {
                    answer[3]++;
                    break;
                } else if (nowNodeOut.size() == 0) {
                    answer[2]++;
                    break;
                } else if (nowNodeOut.get(0).getNumber() == startNodeNumber) {
                    answer[1]++;
                    break;
                }

                nowNodeNumber = nowNodeOut.get(0).getNumber();
            }
        }

        return answer;
    }

    private int findNewNode(Node[] nodes) {
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }
            
            if (nodes[i].getIn().size() == 0 && nodes[i].getOut().size() >= 2) {
                return i;
            }
        }

        return 0;
    }

    private class Node {

        private final int number;
        private List<Node> in;
        private List<Node> out;

        public Node(int number, List<Node> in, List<Node> out) {
            this.number = number;
            this.in = in;
            this.out = out;
        }

        public int getNumber() {
            return number;
        }

        public List<Node> getIn() {
            return in;
        }

        public List<Node> getOut() {
            return out;
        }

        public void addIn(Node node) {
            in.add(node);
        }

        public void addOut(Node node) {
            out.add(node);
        }
    }
}