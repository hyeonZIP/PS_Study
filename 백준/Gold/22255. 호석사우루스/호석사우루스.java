import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = -1;

    static class Node {
        int y, x, point, count;

        public Node(int y, int x, int point, int count) {
            this.y = y;
            this.x = x;
            this.point = point;
            this.count = count % 3;
        }
    }

    static int N, M, startY, startX, endY, endX;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        bfs();
    }

    static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.point));
        int[][][] dist = new int[N + 1][M + 1][3];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }

        }
        dist[startY][startX][1] = 0;

        pq.offer(new Node(startY, startX, 0, 1));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.count % 3 == 0) {
                // 3K번째
                moveVertically(pq, dist, cur);
                moveHorizontality(pq, dist, cur);
            } else if (cur.count % 3 == 1) {
                // 3K+1번 째
                moveVertically(pq, dist, cur);
            } else {
                // 3K+2번 째
                moveHorizontality(pq, dist, cur);
            }
        }

        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, dist[endY][endX][i]);
        }

        answer = answer == Integer.MAX_VALUE ? -1 : answer;
    }

    static void moveVertically(PriorityQueue<Node> pq, int[][][] dist, Node cur) {
        int y = cur.y;
        int x = cur.x;
        int count = cur.count;

        int[] dy = new int[] { -1, 1 };

        for (int i = 0; i < 2; i++) {
            int py = dy[i] + y;
            int px = x;
            int nextCount = (count + 1) % 3;

            if (isOutOfRange(py, px) || map[py][px] == WALL) {
                continue;
            }

            if (dist[py][px][nextCount] > dist[y][x][count] + map[py][px]) {
                dist[py][px][nextCount] = dist[y][x][count] + map[py][px];

                pq.offer(new Node(py, px, dist[py][px][nextCount], count + 1));
            }
        }
    }

    static void moveHorizontality(PriorityQueue<Node> pq, int[][][] dist, Node cur) {
        int y = cur.y;
        int x = cur.x;
        int count = cur.count;

        int[] dx = new int[] { -1, 1 };

        for (int i = 0; i < 2; i++) {
            int py = y;
            int px = x + dx[i];
            int nextCount = (count + 1) % 3;

            if (isOutOfRange(py, px) || map[py][px] == WALL) {
                continue;
            }

            if (dist[py][px][nextCount] > dist[y][x][count] + map[py][px]) {
                dist[py][px][nextCount] = dist[y][x][count] + map[py][px];

                pq.offer(new Node(py, px, dist[py][px][nextCount], count + 1));
            }
        }
    }

    static boolean isOutOfRange(int y, int x) {
        return y <= 0 || x <= 0 || y > N || x > M;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        st = new StringTokenizer(br.readLine());
        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());
        endX = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}