import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{S, T});
        }

        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        pq2.offer(0);

        for (int i=0; i<N; i++){
            int arr[] = pq.poll();
            if (pq2.peek() <= arr[0]){
                pq2.poll();
            }
            pq2.offer(arr[1]);
        }

        System.out.println(pq2.size());
    }
}
