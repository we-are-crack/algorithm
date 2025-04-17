import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Editor editor = new Editor(br.readLine());
        int m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "L":
                    editor.L();
                    break;
                case "D":
                    editor.D();
                    break;
                case "B":
                    editor.B();
                    break;
                case "P":
                    editor.P(st.nextToken().charAt(0));
                    break;
            }
        }

        System.out.println(editor);
    }

    private static class Editor {
        private Node root;
        private Node cursor;

        public Editor(String str) {
            root = new Node();
            cursor = new Node();

            root.setNext(cursor);
            cursor.setPrev(root);

            for (char ch : str.toCharArray()) {
                P(ch);
            }
        }

        public void L() {
            if (cursor.prev.isRoot()) {
                return;
            }

            cursor = cursor.prev;
        }

        public void D() {
            if (cursor.isLast()) {
                return;
            }

            cursor = cursor.next;
        }

        public void B() {
            if (cursor.prev.isRoot()) {
                return;
            }

            Node removeTarget = cursor.prev;
            removeTarget.prev.setNext(cursor);
            cursor.setPrev(removeTarget.prev);
        }

        public void P(char ch) {
            if (cursor.isRoot()) {
                return;
            }

            Node newNode = new Node(cursor.prev, cursor, ch);
            cursor.prev.setNext(newNode);
            cursor.setPrev(newNode);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            Node cur = root.next;
            while (!cur.isLast()) {
                sb.append(cur.ch);
                cur = cur.next;
            }

            return sb.toString();
        }
    }

    private static class Node {
        private Node prev;
        private Node next;
        private Character ch;

        public Node() {}

        public Node(Node prev, Node next, Character ch) {
            this.prev = prev;
            this.next = next;
            this.ch = ch;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public boolean isRoot() {
            return ch == null && prev == null;
        }

        public boolean isLast() {
            return ch == null && next == null;
        }
    }
}
