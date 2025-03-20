import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int V, E;
    static int[] yogurtNode = new int[10];
    static long[] yogurtDist = new long[10];
    static long[] myDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; i++) {
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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            yogurtNode[i] = Integer.parseInt(st.nextToken());
        }

        int start = Integer.parseInt(br.readLine());

        yogurtDijkstra();
//        for (long l : yogurtDist) {
//            System.out.print(l + " ");
//        }
//        System.out.println();
        System.out.println(myDijkstra(start));


    }//main

    private static long myDijkstra(int start) {
//        System.out.println("요거트 아줌마 레코드");
//        for (long l : yogurtDist) {
//            System.out.print(l + " ");
//        }
//        System.out.println();
        int answer = Integer.MAX_VALUE;
//        System.out.println("내 움직임 레코드,");
        for (int i = 0; i < 10; i++) {
//            System.out.println("start = " + start);
//            System.out.println("yogurtNode = " + yogurtNode[i]);
            long minDist = dijkstra(start, yogurtNode[i]);
//            System.out.print(minDist + " ");
            if (minDist == -1) continue;
//            System.out.print("yogurtDist = " + yogurtDist[i]);
//            System.out.println(" minDist = " + minDist);
//            System.out.println("현재 인덱스 i = " + i);
            if (yogurtDist[i] != Long.MAX_VALUE && yogurtDist[i] >= minDist) {
                answer = Math.min(answer, yogurtNode[i]);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static void yogurtDijkstra() {
        Arrays.fill(yogurtDist, Long.MAX_VALUE);
        yogurtDist[0] = 0;

        int start = 0;
        for (int i = 1; i < 10; i++) {
            long minDist = dijkstra(yogurtNode[start], yogurtNode[i]);

            if (minDist == -1) continue;

            yogurtDist[i] = yogurtDist[start] + minDist;
            start = i;
        }
    }

    private static long dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));
        pq.offer(new Node(start, 0));

        boolean[] visited = new boolean[V + 1];

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            if (cur.end == end) {
                return cur.weight;
            }

            if (!visited[cur.end]) {
                visited[cur.end] = true;

                for (Node next : adj.get(cur.end)) {

                    if (!visited[next.end]) {
                        pq.offer(new Node(next.end, next.weight + cur.weight));
                    }
                }
            }
        }
        return -1;
    }
}
