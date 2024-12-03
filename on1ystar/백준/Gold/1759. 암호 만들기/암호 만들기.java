import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int l, c;
    private static char[] alphabets;
    private static final StringBuilder sb = new StringBuilder();
    private static final char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        alphabets = new char[c];
        for (int i = 0; i < c; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabets);
        
        findPassword(0, new StringBuilder());

        System.out.println(sb);
    }

    private static void findPassword(int start, StringBuilder password) {
        if (password.length() == l) {
            if (valid(password)) { sb.append(password).append("\n"); }
            return;
        }

        for (int i = start; i < c; i++) {
            password.append(alphabets[i]);
            findPassword(i + 1, password);
            password.deleteCharAt(password.length() - 1);
        }
    }

    private static boolean valid(StringBuilder password) {
        int vowel = 0;
        int consonant = 0;

        for (int i = 0; i < l; i++) {
            if (isVowel(password.charAt(i))) {
                vowel++;
            } else {
                consonant++;
            }

            if (vowel >= 1 && consonant >= 2) {
                return true;
            }
        }

        return false;
    }

    private static boolean isVowel(char c) {
        for (int i = 0; i < 5; i++) {
            if (vowels[i] == c) {
                return true;
            }
        }

        return false;
    }
}