import java.io.*;
import java.util.*;

public class Main {
    public static String answer;
    public static List<Integer> inputCase = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();

        sol();

        print();
    }

    public static void sol() {
        long[] dp = new long[31];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= 30; i++) {
            long sum = 0;

            for (int j = 0; j < i; j++) {
                sum += dp[j] * dp[i - j - 1];
            }

            dp[i] = sum;
        }

        StringBuilder sb = new StringBuilder();

        for (int input : inputCase) {
            sb.append(dp[input]).append("\n");
        }

        answer = sb.toString();
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int number = Integer.parseInt(br.readLine());

            if (number == 0) break;

            inputCase.add(number);
        }
    }

    public static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer);
        bw.close();
    }
}
