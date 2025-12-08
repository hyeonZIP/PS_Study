import java.io.*;
import java.util.*;

public class Main {
    private static double A;
    private static int B;
    private static int answer;
    private static double[] logFact;

    public static void main(String[] args) throws IOException {
        init();

        sol();

        answer();
    }

    private static void sol() {
        double hitRate = 0.0;

        for (int i = 0; i <= B; i++) {
            double d = logFact[B] - logFact[i] - logFact[B - i];
            
            if (i > 0) {
                d += i * Math.log(A);
            }
            if (B - i > 0) {
                d += (B - i) * Math.log(1 - A);
            }

            hitRate += Math.exp(d);

            if (hitRate >= 0.05) {
                answer = i;
                return;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Double.parseDouble(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        logFact = new double[B + 1];
        logFact[0] = 0;

        for (int i = 1; i <= B; i++) {
            logFact[i] = logFact[i - 1] + Math.log(i);
        }

    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}