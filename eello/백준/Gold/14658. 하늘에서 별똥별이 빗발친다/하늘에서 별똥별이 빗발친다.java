import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Star[] stars = new Star[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars[i] = new Star(i, y, x);
        }

        List<Trampoline> trampolines = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Star star1 = stars[i];
            for (int j = i + 1; j < k; j++) {
                Star star2 = stars[j];
                if (Trampoline.isCreatable(l, star1, star2)) {
                    trampolines.add(new Trampoline(l, star1, star2));
                }
            }
        }

        int blocking = 1;
        for (Trampoline trampoline : trampolines) {
            for (Star star : stars) {
                trampoline.add(star);
            }

            blocking = Math.max(blocking, trampoline.stars.size());
        }

        System.out.println(k - blocking);
    }

    private static class Trampoline {

        private int size;
        private int y, x;
        private Set<Integer> stars = new HashSet<>();

        public Trampoline(int size, Star s1, Star s2) {
            this.size = size;
            y = Math.min(s1.y, s2.y);
            x = Math.min(s1.x, s2.x);
            stars.add(s1.id);
            stars.add(s2.id);
        }

        public static boolean isCreatable(int l, Star star1, Star star2) {
            return Math.abs(star1.y - star2.y) <= l
                    && Math.abs(star1.x - star2.x) <= l;
        }

        public void add(Star star) {
            if (!addable(star)) {
                return;
            }

            stars.add(star.id);
        }

        private boolean addable(Star star) {
            return y <= star.y && star.y - y <= size
                    && x <= star.x && star.x - x <= size;
        }
    }

    private static class Star {

        private int id;
        private int y;
        private int x;

        public Star(int id, int y, int x) {
            this.id = id;
            this.y = y;
            this.x = x;
        }
    }
}
