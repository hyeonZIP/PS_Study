import java.io.*;
import java.util.*;

public class Main {
    private static int answer;
    private static int[][][] dp;
    private static boolean[][][] visited;
    private static int[] inputs;
    private static int opened1, opened2;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        answer = dfs(0, opened1, opened2);
    }

    private static int dfs(int depth, int left, int right) {
        if (depth == inputs.length) {
            return 0;
        }

        if (visited[depth][left][right]) {
            return dp[depth][left][right];
        }

        int current = inputs[depth];
        int min = Integer.MAX_VALUE;

        if (current != left) {
            int rightDiff = Math.abs(right - current);
            int nextLeft = Math.min(current, left);
            int nextRight = Math.max(current, left);

            min = Math.min(min, dfs(depth + 1, nextLeft, nextRight) + rightDiff);
        }

        if (current != right) {
            int leftDiff = Math.abs(left - current);
            int nextLeft = Math.min(current, right);
            int nextRight = Math.max(current, right);

            min = Math.min(min, dfs(depth + 1, nextLeft, nextRight) + leftDiff);
        }

        dp[depth][left][right] = min;
        visited[depth][left][right] = true;

        return dp[depth][left][right];
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        opened1 = Integer.parseInt(st.nextToken());
        opened2 = Integer.parseInt(st.nextToken());

        int usingCount = Integer.parseInt(br.readLine());

        inputs = new int[usingCount];
        dp = new int[usingCount][21][21];
        visited = new boolean[usingCount][21][21];

        for (int i = 0; i < usingCount; i++) {
            inputs[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}