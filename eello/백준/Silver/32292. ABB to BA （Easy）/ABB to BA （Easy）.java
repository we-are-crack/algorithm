import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		while (t-- > 0) {
			int len = Integer.parseInt(br.readLine());
			String str = br.readLine();

			int find = str.indexOf("ABB");
			while (find != -1) {
				str = str.replace("ABB", "BA");
				find = str.indexOf("ABB");
			}

			answer.append(str).append("\n");
		}

		System.out.print(answer);
	}
}
