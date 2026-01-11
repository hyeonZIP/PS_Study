import java.io.*;
import java.util.*;

public class Main {
    private static class Input {
        private int S, E;

        public Input(int S, int E) {
            this.S = S;
            this.E = E;
        }
    }

    private static StringBuilder answer = new StringBuilder();
    private static int[] arr;
    private static int N, M;
    private static Input[] input;
    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        palindrome();

        for (int i = 0; i < M; i++) {
            int s = input[i].S;
            int e = input[i].E;

            if (dp[s][e]) {
                answer.append(1).append("\n");
            } else {
                answer.append(0).append("\n");
            }
        }
    }

    private static void palindrome() {
        // S~E 길이가 1인경우
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // S~E 길이가 2인경우
        for (int i = 1; i <= N - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // S~E 길이가 3이상인 경우
        for (int i = 2; i < N; i++) {
            for (int j = 1; j <= N - i; j++) {
                if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.close();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        input = new Input[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            input[i] = new Input(S, E);
        }

        dp = new boolean[N + 1][N + 1];
    }
}