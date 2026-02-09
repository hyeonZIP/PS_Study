import java.io.*;
import java.util.*;

public class Main {
    private static class Infection {
        int node;
        int time;

        public Infection(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    private static int testcase;
    private static int[] input;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());// 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken());// 의존성 개수
            int c = Integer.parseInt(st.nextToken());// 해킹당한 컴퓨터의 번호

            List<Infection>[] adj = new ArrayList[n + 1];

            for (int j = 1; j <= n; j++) {
                adj[j] = new ArrayList<>();
            }

            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());// 컴퓨터 a
                int b = Integer.parseInt(st.nextToken());// 컴퓨터 b
                int s = Integer.parseInt(st.nextToken());// a가 b를 의존하며 b가 감염되면 s초 후 a 감염

                adj[b].add(new Infection(a, s));
            }

            sol(n, c, adj);
        }

        print();
    }

    private static void sol(int computerCount, int startComputerNumber, List<Infection>[] computerDependency) {
        int[] dist = new int[computerCount + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[startComputerNumber] = 0;

        PriorityQueue<Infection> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

        q.offer(new Infection(startComputerNumber, 0));

        while (!q.isEmpty()) {
            Infection current = q.poll();

            if (current.time > dist[current.node]) {
                continue;
            }

            for (Infection next : computerDependency[current.node]) {
                if (dist[next.node] > dist[current.node] + next.time) {
                    dist[next.node] = dist[current.node] + next.time;

                    q.offer(new Infection(next.node, dist[current.node] + next.time));
                }
            }
        }

        int infectedComputerCount = 0;
        int infectingTime = 0;

        for (int i = 1; i <= computerCount; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                infectedComputerCount++;
                infectingTime = Math.max(dist[i], infectingTime);
            }
        }

        answer.append(infectedComputerCount).append(" ").append(infectingTime).append("\n");

    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.close();
    }
}