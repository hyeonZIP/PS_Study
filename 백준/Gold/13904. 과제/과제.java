import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static int N;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(
            Comparator
                    .comparingInt((int[] o) -> -o[1])
                    .thenComparing(o -> o[0]));

    public static void main(String[] args) throws IOException {
        init();
        sol();
        print();
    }

    static void sol() {
        int[] maximum = new int[1001];
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();

            int d = arr[0];
            int w = arr[1];

            for (int i = d; i >= 1; i--) {
                if (maximum[i] == 0) {
                    maximum[i] = w;
                    break;
                }
            }
        }

        for (int i = 1; i <= 1000; i++) {
            answer += maximum[i];
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new int[] { d, w });
        }
    }

    static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.close();
    }
}