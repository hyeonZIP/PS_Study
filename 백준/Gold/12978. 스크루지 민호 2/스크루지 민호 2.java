import java.io.*;
import java.util.*;

public class Main {
    private static final int BUILDING = 1;
    private static final int SKIP = 0;

    private static int answer;
    private static int N;
    private static ArrayList<Integer>[] adj;
    private static int[][] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        dfs(1);

        answer = Math.min(dp[1][SKIP], dp[1][BUILDING]);
    }

    private static void dfs(int current) {
        visited[current] = true;
        dp[current][SKIP] = 0;
        dp[current][BUILDING] = 1;

        for (int next : adj[current]) {
            if (visited[next]) {
                continue;
            }
            dfs(next);
            dp[current][SKIP] += dp[next][BUILDING];
            dp[current][BUILDING] += Math.min(dp[next][BUILDING], dp[next][SKIP]);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}