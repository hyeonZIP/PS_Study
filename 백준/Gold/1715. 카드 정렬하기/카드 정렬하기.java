import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i=0; i<N; i++){
            pq.offer(Long.parseLong(br.readLine()));
        }
        if (N > 1){
            long count = 0;

            while(pq.size() >= 2){
                long a = pq.poll();
                long b = pq.poll();
                count += a+b;
                pq.offer(a+b);
            }

            System.out.println(count);
        }else{
            System.out.println(0);
        }

    }
}