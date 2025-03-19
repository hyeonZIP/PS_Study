import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, start;
    static ArrayList<int[]>[] adj;
    static int[] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());

        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }
        dis = new int[V + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[start].add(new int[]{end, weight});
        }

        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(dis[i]).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(sb + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curPos = cur[0];
            int curWei = cur[1];

            for (int[] next : adj[curPos]) {
                int nextPos = next[0];
                int nextWei = next[1] + curWei;
                if (dis[nextPos] > nextWei) {
                    dis[nextPos] = nextWei;
                    pq.offer(new int[]{nextPos, nextWei});
                }
            }
        }
    }
}
