
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1 3 5 6 10
 *  2 2 1 4
 * 혼자면 비용이 안든다
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        for (int i=0; i<N-1; i++){
            pq.offer(new int[]{arr[i],arr[i+1],arr[i+1]-arr[i]});
        }

        int answer =0;
        for (int i=0; i<N-K; i++){
            answer+=pq.poll()[2];
        }

        System.out.println(answer);
    }
}
