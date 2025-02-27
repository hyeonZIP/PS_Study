
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
//            arr[i] = Integer.parseInt(st.nextToken());
            pq.offer(Long.parseLong(st.nextToken()));
        }

        for (int i=0; i<m; i++){
            Long n1 = pq.poll();
            Long n2 = pq.poll();
            Long n3 = n1 + n2;
            pq.offer(n3);
            pq.offer(n3);
        }

        Long sum = 0L;
        while(!pq.isEmpty()){
            sum += pq.poll();
        }
        System.out.println(sum);
    }
}
