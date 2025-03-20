import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final long INF = Long.MAX_VALUE;

    static public class Node {
        int end;
        long weight;

        public Node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static ArrayList<ArrayList<Node>> reverseAdj = new ArrayList<>();
    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());//N명의 학생
        M = Integer.parseInt(st.nextToken());//M개의 도로
        X = Integer.parseInt(st.nextToken());//X번 파티

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
            reverseAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Node(b, c));
            reverseAdj.get(b).add(new Node(a,c));
        }

        long[] dist1 = dijkstra(adj);
        long[] dist2 = dijkstra(reverseAdj);

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dist1[i]+dist2[i]);
        }
        System.out.println(answer);

    }

    private static long[] dijkstra(ArrayList<ArrayList<Node>> adj) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));
        pq.offer(new Node(X, 0));
        

        long[] dist = new long[N + 1];
        Arrays.fill(dist,INF);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.end] < cur.weight) continue;

            for (Node next : adj.get(cur.end)) {
                if (dist[next.end] > dist[cur.end] + next.weight) {
                    dist[next.end] = dist[cur.end] + next.weight;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }

        return dist;
    }
}
