import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int minLen = Integer.MAX_VALUE;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N; i++){
            int cat = Integer.parseInt(st.nextToken());
            arr[i] = cat;
            if (cat == 1){
                q.offer(i);
            }

            if (q.size()==K){
                minLen = Math.min(minLen, i - q.poll()+1);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        if (minLen == Integer.MAX_VALUE){
            bw.write(-1+"");
        }
        else{
            bw.write(minLen +"");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
