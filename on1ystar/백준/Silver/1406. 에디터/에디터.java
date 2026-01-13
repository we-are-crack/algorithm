import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        List<Character> ll = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            ll.add(s.charAt(i));
        }

        int n = Integer.parseInt(br.readLine());

        ListIterator<Character> llIter = ll.listIterator();

        while (llIter.hasNext()) {
            llIter.next();
        }

        for (int i = 0; i < n; i++) {
            String command = br.readLine();

            switch (command.charAt(0)) {
                case 'L':
                    if (llIter.hasPrevious()) {
                        llIter.previous();
                    }
                    break;
                case 'D':
                    if (llIter.hasNext()) {
                        llIter.next();
                    }
                    break;
                case 'B':
                    if (llIter.hasPrevious()) {
                        llIter.previous();
                        llIter.remove();
                    }
                    break;
                case 'P':
                    llIter.add(command.charAt(2));
            }
        }

        for (char c : ll) {
            bw.write(c);
        }

        bw.flush();
        bw.close();
    }
}