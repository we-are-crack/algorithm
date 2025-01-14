import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] visitor = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            visitor[i] = Integer.parseInt(st.nextToken());
        }

        int sectionVisitor = 0;
        for (int i = 0; i < x; i++) {
            sectionVisitor += visitor[i];
        }

        int left = 0, right = x - 1;
        int maxSectionVisitor = sectionVisitor, maxSectionCount = 1;
        while (right < n - 1) {
            sectionVisitor -= visitor[left++];
            sectionVisitor += visitor[++right];

            if (maxSectionVisitor < sectionVisitor) {
                maxSectionVisitor = sectionVisitor;
                maxSectionCount = 1;
            } else if (maxSectionVisitor == sectionVisitor) {
                maxSectionCount++;
            }
        }

        StringBuilder answer = new StringBuilder();
        if (maxSectionVisitor == 0) {
            answer.append("SAD");
        } else {
            answer.append(maxSectionVisitor).append("\n");
            answer.append(maxSectionCount);
        }

        System.out.println(answer);
    }
}
