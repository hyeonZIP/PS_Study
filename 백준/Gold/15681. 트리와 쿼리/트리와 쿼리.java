import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> tree = new ArrayList<>();
    private static List<List<Integer>> children = new ArrayList<>();
    private static int[] dp;
    private static boolean[] visited;
    private static int N, R, Q;
    private static List<Integer> query = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();

        sol();

        print();
    }

    private static void sol() {
        makeTree(R, -1);

        countSubtreeNodes(R);
    }

    private static void countSubtreeNodes(int current) {
        dp[current] = 1;

        for (int child : children.get(current)) {
            countSubtreeNodes(child);
            dp[current] += dp[child];
        }
    }

    private static void makeTree(int current, int parent) {
        for (int next : tree.get(current)) {
            if (next != parent) {
                children.get(current).add(next);
                makeTree(next, current);
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
            children.add(new ArrayList<>());
        }

        dp = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        for (int i = 0; i < Q; i++) {
            query.add(Integer.parseInt(br.readLine()));
        }
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        for (int q : query) {
            sb.append(dp[q]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}
