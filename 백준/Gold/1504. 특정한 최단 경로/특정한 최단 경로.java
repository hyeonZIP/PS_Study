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

    static public class Node {
        int end;
        long weight;

        public Node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static ArrayList<ArrayList<Node>> adj = new ArrayList<>();
    static boolean[] visited;
    static int N, E;
    static int v1, v2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        //해당 정점을 반드시 지나가야한다
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        //해당 경로를 지나갈 수 없으면 -1 출력
        long answer1 = 0;
        answer1 = dijkstra(1, v1, answer1);
        answer1 = dijkstra(v1, v2, answer1);
        answer1 = dijkstra(v2, N, answer1);

        long answer2 = 0;
        answer2 = dijkstra(1, v2, answer2);
        answer2 = dijkstra(v2, v1, answer2);
        answer2 = dijkstra(v1, N, answer2);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long answer = Math.min(answer1, answer2);

        if (answer == Long.MAX_VALUE) bw.write("-1");
        else bw.write(answer+"");

        bw.flush();
        bw.close();
        br.close();
    }

    private static long dijkstra(int start, int end, long weight) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));
        pq.offer(new Node(start, weight));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = weight;

//        visited = new boolean[N+1];

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.weight > dist[current.end]) continue;

            for (Node next : adj.get(current.end)) {

                if (dist[next.end] > dist[current.end] + next.weight) {
                    dist[next.end] = dist[current.end] + next.weight;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }

        return dist[end];


    }
}
