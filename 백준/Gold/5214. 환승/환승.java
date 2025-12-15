import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, M;
    private static List<Integer>[] adj;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        answer();
    }

    private static void sol() {
        if (N == 1) {
            answer = 1;
            return;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N + M + 1];

        q.offer(new int[] { 1, 1 });
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int current = arr[0];
            int count = arr[1];

            for (int next : adj[current]) {
                if (next == N) {
                    answer = count + 1;
                    return;
                }

                if (visited[next]) {
                    continue;
                }

                if (next <= N) {
                    q.offer(new int[] { next, count + 1 });
                } else {
                    q.offer(new int[] { next, count });
                }

                visited[next] = true;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + M + 1];

        for (int i = 1; i <= N + M; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int hyperTubeNode = N + i + 1;

            for (int j = 0; j < K; j++) {
                int station = Integer.parseInt(st.nextToken());

                adj[station].add(hyperTubeNode);
                adj[hyperTubeNode].add(station);
            }
        }
    }

    private static void answer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        bw.write(String.valueOf(answer));
        bw.close();
    }
}