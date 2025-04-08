import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            long n = Long.parseLong(br.readLine());

            if (n <= 1) n = 2;

            while (true) {
                int count = 0;

                for (int j = 2; j <= Math.sqrt(n); j++) {
                    if (n % j == 0) {
                        count++;
                        break;
                    }
                }

                if (count == 0) {
                    sb.append(n).append("\n");
                    break;
                }
                n++;
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
