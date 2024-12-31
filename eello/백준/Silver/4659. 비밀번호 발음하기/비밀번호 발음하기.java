import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Pattern con1 = Pattern.compile("^[^aeiou]+$");
        Pattern con2 = Pattern.compile(".*[aeiou]{3}.*|.*[^aeiou]{3}.*");
        Pattern con3 = Pattern.compile(".*([^eo])\\1.*");

        String pwd;
        StringBuilder answer = new StringBuilder();
        while (!"end".equals(pwd = br.readLine())) {
            answer.append("<")
                    .append(pwd)
                    .append("> is ");

            if (con1.matcher(pwd).matches()
                    || con2.matcher(pwd).matches()
                    || con3.matcher(pwd).matches()) {
                answer.append("not ");
            }

            answer.append("acceptable.\n");
        }

        System.out.println(answer);
    }
}
