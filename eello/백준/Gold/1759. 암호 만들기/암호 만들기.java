import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int l = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		char[] input = new char[c];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			input[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(input);

		Set<String> pwds = new TreeSet<>();
		comb(pwds, input, new char[l], 0, 0, 0);

		StringBuilder answer = new StringBuilder();
		for (String pwd : pwds) {
			answer.append(pwd).append('\n');
		}

		System.out.print(answer);
	}

	private static void comb(Set<String> pwds, char[] charArray, char[] pwd, int i, int k, int vowel) {
		if (k == pwd.length) {
			if (1 <= vowel && 1 < pwd.length -  vowel)
				pwds.add(new String(pwd));
			return;
		}

		if (charArray.length == i) {
			return;
		}

		pwd[k] = charArray[i];
		comb(pwds, charArray, pwd, i + 1, k + 1, vowel + (isVowel(charArray[i]) ? 1 : 0));

		comb(pwds, charArray, pwd, i + 1, k, vowel);
	}

	private static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
