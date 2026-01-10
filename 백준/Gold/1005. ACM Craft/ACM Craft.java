import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder answer = new StringBuilder();
    private static int T;
    private static int[][] buildTime;
    private static List<Integer>[][] buildOrder;
    private static int[] target;
    private static int[][] dp;
    private static int[][] buildNeeds;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    private static void sol() {
        for (int testCase = 0; testCase < T; testCase++) {
            bfs(testCase);
        }
    }

    private static void bfs(int testCase) {
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 1; i < buildNeeds[testCase].length; i++) {
            if (buildNeeds[testCase][i] == 0) {
                dp[testCase][i] = buildTime[testCase][i];
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : buildOrder[testCase][current]) {
                dp[testCase][next] = Math.max(dp[testCase][next], dp[testCase][current] + buildTime[testCase][next]);
                buildNeeds[testCase][next]--;

                if (buildNeeds[testCase][next] == 0) {
                    q.offer(next);
                }
            }
        }

        answer.append(dp[testCase][target[testCase]]).append("\n");
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        buildTime = new int[T][];
        buildOrder = new ArrayList[T][];
        target = new int[T];
        dp = new int[T][];
        buildNeeds = new int[T][];

        StringTokenizer st;

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 개수 1~N
            int K = Integer.parseInt(st.nextToken()); // 규칙 개수

            buildTime[testCase] = new int[N + 1];
            buildOrder[testCase] = new ArrayList[N + 1];
            dp[testCase] = new int[N + 1];
            buildNeeds[testCase] = new int[N + 1];

            Arrays.fill(dp[testCase], Integer.MIN_VALUE);

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                buildTime[testCase][j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 1; j <= N; j++) {
                buildOrder[testCase][j] = new ArrayList<>();
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                buildOrder[testCase][a].add(b); // 단방향
                buildNeeds[testCase][b]++;
            }

            target[testCase] = Integer.parseInt(br.readLine());
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.close();
    }
}