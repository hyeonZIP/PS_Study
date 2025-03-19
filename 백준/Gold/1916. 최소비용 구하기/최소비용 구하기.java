import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static List<List<Node>> adj = new ArrayList<>();
    static int N, M;
    static int[] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());

        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        dijkstra(startNode);

        System.out.println(dis[endNode]);
    }

    private static void dijkstra(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        pq.offer(new Node(startNode, 0));

        dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[startNode] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();


            if (dis[cur.end] < cur.weight) continue;

            for (Node next : adj.get(cur.end)) {

                if (dis[next.end] > dis[cur.end] + next.weight) {
                    dis[next.end] = dis[cur.end] + next.weight;
                    pq.offer(new Node(next.end, dis[next.end]));
                }

            }
        }
    }
}
